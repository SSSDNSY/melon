<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">MELON</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          type="text"
          auto-complete="off"
          placeholder="账号"
        >
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
        </el-input>
      </el-form-item>
      <el-form-item prop="code" v-if="captchaEnabled">
        <el-input
          v-model="loginForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <div style="float: right;" v-if="register">
          <router-link class="link-type" :to="'/register'">立即注册</router-link>
        </div>
        <div style=" color: rgb(155, 155, 155);margin:0 auto;"><span style="vertical-align: middle;">第三方登录:</span>
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAABJdJREFUWEetl31sE3UYx7/P3fXaGKbjZezlOhEIYJgbhBcV4zpHjInJYvxH1KAumBjQEAUSo4lvAf/RBMc0khhAHME39B8T0MRE2VqjbLKJjoBjZGSbPTo2Rrexdeu1d4+5skLb3V3b6e/P7fv7Pp88b78rIcdT1jy2gERPHTGqGahkUDmBC83rDB4hUD8zOonwiyi4jvdXUzgXa8omKj2prRUkvALGs9m0af8nNIHQqFbLfzndcwRQ/No+ADvyCpwhJvDeYI37VTsPS4CSQHSlxHSEgXX/JXjK3VbEjHr1YU93pt8MAKVZux8CTgCY/z8Fn7ahEBPXXfbJf6T6pgHceTJaoYvkdwp+hwRsLxex7DagskCAwYyOMUZ3BGjo07MwU4ig1wRrPBeTwlsAzKQEYu0A1ti5VM0hNN0jYaFsrThznbG7R0f7GNuDMFrVh+QNMwDK/NqHBLxsd/N2CTj/gCtrVa7GgMfOxNE/ZQ+R2piJDHgD2r3MaHNy/2C5iCdLhKwApuBT1cA7PVnKIWC1OaJJgC+Z8bSd+yPzBRyuEHMKnhStb4sjFHUoBaFJ9clb6K5mLokJsZCT+4vlAt5YnB/Atr91nBgyHKFFwTWPvAHtBWYccFLuXS7iqRzTn/Q5GDSw+1K2qUA9KS3RoyB6xqn5vq2SUDEn69ZOszg3zniiM46xuMNAEA6REtD+BGOVnWxzqYD3l+WX/qTXaxd1fBFyKANRGyn+2DWA59oB7FokYtei3Lo/06Ohz3BcTsw8QIpfc2hV4OsqCQ8W5pf+JMipkRtlcDhTjgBbvQLeWjK79CeD7rmk40DQtgwJAPPDIfFhkXqKZcIPayQU26zdfJaCNxCzlDPILEG0E6DKTMVsa98cNtATAUbijPE4MGkg8Vh1TVhW+jSV+bXPCdicCXBwpYhHFwgITjG8nuw98M2AgQsRxrjF6PdOMn4dmQlAwGGzBFsBfJIJ8PHdIh5fKGDnBR37Vjj3wf5/DMvHR51ilLoJQzHgp2GLPiBsIe/PEYUlKZgJ8PpiEV43sL1Lx+n7pISR1flMNdAVsR6klmsM8xVVPGQJwMZUUcJV8WvHAGyya6xtXgFvWkxDd4QTL5/VOXud0Tnu+BgdVX3yczcAWmIbQPyblVGRDLy9RETtPAGFUrriu0EDp0ZnBokaQN+k+ZXEGLVZA4aOdaGNcsfNvCp+bT+AlzIh9iwV8bxivQnf69URtp6whM0Vzab2QKNaI+80NTcB1raza2Ai8UlWlQrRsELEpmJrgEOqgUDYsO0Pc4L84fQMEdAerJHXJ2OkdZbSrK2GgACAgqSgxE14d6mAVQWEIpkwqDEGNeDHqwaOXTEwpCEBUO4BCiXCXBegGcCEDrSNGpklGNaJfQM+93lLAPOPpS2xaoH4+1SIfLaeg3YYBurUWrk1VWM5W9OZOJJZjlmDEDrEONf3b3Sfy/SwXXHTPdFo1Zj5gDDw0WWfaweILGcy646dHlHz96HtnrACIsJXABqDPvl3J+CsAMnL0xuzjoFqAlcBVH7rFaUwiPth4CwJCEi663hvLQ3kkql/AcnHwKHS9ks3AAAAAElFTkSuQmCC"
            width="22" height="22" title="QQ" style="vertical-align: middle; margin-left: .8em;">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA6ZJREFUWEfFV11oFFcU/s7EBBRUEFOwIih9kPrgT7d7NxVqjdm5Y8A38QfFB18qTfVFBBELtaAIQvEhqPiglVIQVEqVgtl7Rw0rweyshlgoNg9t1YAK+iARsWy6c8pMdrbr7M7MRgw7b/fec77zzbnn7xKm8ZkqvZoMrAPTKoCWE/FyT52ZxgAeA/F9dpHXsjjaLCwlCWZ118cG3H0ANgFYkiRfOR8H8KsLo982hx/E6cQSkFocBeAZn9ek4bDYBIB+ZTrfROlHEpBa5AF8/o6Gw2q3lemsa4TVkICpxXMCFr4n4z4MAy+06XSGMesISFv8DcbS92m8ikV4qLLOslrstwhILS4B2DIjxv8HvaxMZ2uwrBKoBNzhGTYewB8LAtMnUEm14ZhoL8HFcbTRH2XwSEeJnk2282cGsBLANgCpaRKfcGF0eSnqE5BanAbwVQTIVbjGcWUNF6KMSJ35EuCz0yRxRplOH2V1ar6BtkcA5jcAuF2aNUcOdg/+kwRuKrGFCF4MNfnRE2UWFpOlMruY+Mc6LcIrY9JND/TeHat46S7ABWUWv/bWG693LXVn8RARVC5b2O3L5MQRGPi2SQYgw82Q1OkrAG0OKzF4UJvF7mDfsjM/cJkfKcs54u315td0lkvt51DGiLcnc+klTPQpEX5ulgCA/WRpMcLAmjoCRN/rbOFAEpilxQADVkXuJBn4Cy4Eg3oA/jBB/zxJJR6D6psMEV0IXBsdfIIB3FGmszYsY+VTi/hN2574K6Ehklq8BjCn/gpQ1KYj4owT40ROOgfj/tLSYi8D/Y1kiPAikgCA1wbR2oFs4bew8vpbqYUd5bZiUFalLa6BeTwIUKkF13pQ2umLYNoexpkiEHEFU8L0BAbvUj3OzVrljXZmZdl1P9GyeMEPSDuzolzGbGUV7nlrLyWZMGGbTs7PDjvdB6ZT9V6gocggrBGeNAhfDGSdO8GezIkNpY7SyGD36MukIA0IRdSI85FpWAVmfq5k8QOv2hG4A6CnLrtziemlspxfmiEQ0+T2RxeiANnFdzD8qWhBrTEGLmrT2ZFEIK5C+oUooRTH4hPhRC4bnQV+bDD/3hikUor9IIlvRrEkvIoJ5pOT7f/mg5jovZH66HrPvT+lFj8B2BkBMNWMvMMm2nGSp4PzUTAvBlEnGOMw+BCYPBLh7+12XPGCNwG3ZiCpplcrR7IqiVYOpQGJlo7lNdfRuodJDYnWPc0CEi19nIaTdyae5/8BTPSjRCroad0AAAAASUVORK5CYII="
            width="22" height="22" title="微信(须配置)" style="vertical-align: middle; margin-left: .8em;">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA+RJREFUWEfFl12IVVUUx3/rpD2EKdEHJEYmWGQRBaFDWtSLYGlTUxn01Afc2XfC8EGCvtABC0XSh8G5e6aHerEPjcIMw6cGpVBBJ0IKzb4lqIxIsijhLtlnn3PPmXPP2eeagQsOHPZea+3/Wnt9beFcyOhCYDFwM3BD8jkNR5PvCPAJVg72qlZqGYf0RtqsQuhHmV3L7xiEn1B2EjHCqHwZkgkDMLoeWAXM7OngbqZTwAhWXqySrwYwqNsRHvmPB08VU3YwJivLdJUDMPotMPd/OTxT8h1Wrivq7AZg9FfgisrDhV0o48DtwNoC3wRwdwD4Saxcmd+fCsDoXuDOoOVt+hiXAzGP0XeBh+J/YSUt2YHRdSXA8ir3YeWudCED4APuheDhwgFa0tfhMfossDH2yJgMdtabegTlpoCul9PA9AB8qu2vjXbB0pJmR3FDHyDifawUPbnVaQ0AOEVEn0tRL2h0FMgUFyWVYwjXAyewck3O0k0oa4iYz6gcz63vR1lUE8QtrAwJT+qlXMz3wGUlAsexMj8B+TjwOrACKx/Ga03dg7J0ylpDFxHF3qyj3/mXawWjDwLvlXILz9CSkZxl+2hzVVzpPPmI9x7aBfyZFC4n4zLiNmBzAMmA0NS3UR4tZeq+27oId2p2Y+W+XJysJmJLhYHvOA8cTpB284QAuMgX3gLmAcsB50lHr2GlkQNwNVHHY8UzJh2AH4AssPIsxSsw+nHi9mGsOG9kZNTVhoUomxgTl56ejLqK6iprGf3oAJwGLgnc0zDKQSKeQhkAPsLKvaX8gzqJ8DVWHu7sN3UZyu4K/X/1AmCqbHotRtdiZbhLsdFfEJ7mDHuZzhzglSRTyjDEAKqvIC9S7GhGv8DKglLLjGrAo/mt+AqqgzBlTQ/3FdNnjCtAYzKDprpidDqOCd8HJjjD50zHxcstNUAmw2nopY/SZgltLmIargC58ctVPXcFzgBnrQ9Ko58Cz2NlgiG9gzZ7gBmVIIQ4DasLkZd8DisbOl0uiwEtAfAZsDoG4Kh+qBmoK8VlbfYJrLwRW97tgWNAowMg3JqTUuxzNdSMXsXKmiSnNyPciuKbmJV78C15GfAH8A/gmoz3gNFtwGMVV5A0I7cbbsc/06a/M4T0GN4M6gIitlfMBYV27NGGBpK/gZeAQ3FQCbPjEV2Yg8afG72/IuJEkiFzERqBoaQwkKRW9TKS9eqBar6KkSwDER5Kzw9AzVCagbiAY3kKoj6He/fFOT9MMk9cwKdZCiJ9nPp8ntWj2a4mvHn+j9P8aQ2dRcT9QH8yBbnX0+UJy2/ASeAbYCdtPmBcHIhaOguhk3tQGHn9SQAAAABJRU5ErkJggg=="
            width="22" height="22" title="百度" style="vertical-align: middle; margin-left: .8em;">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAABCFJREFUWEfFl1uIVWUUx39rX84w5aVI6X4DsQSlG0oSpV0I7GEyPd+MPST4JEU2odFDF5ygiHkKEqY3CUGc47enYIKUSJN8qAydBrLSKEuNirHEyQ7MuewV355zhjPjOfuchpHZL+ds9vr+6/+t9V/rW5/Q4qMdHXMLbW2PCXQJLFZYIHCNW67wl8A5hZMKuczY2KcyOPhPK9DSzGisq2uJH8dbFDYAVzezr3w/L9Bf9rwdbbnc92lrUgkUjXlTwDmf16LjSWYCowo7Qmtfa7S+IYGiMZ8DD07HcZ01h0NrH6qHVZdA0ZgRYMEMOa/CnAutXTgV8xICJWNOKdw2w84TOIFfAmtvr8WeRKBgzF4BczmcVzEVbMbazur7BAEnOODVGXD+DXB3E5y3qsJMCFRK7cvpqr3qLFZ9oy2KenTt2qvKYfiAwkvA6jp5Hy173v2uRBMCJWP6FJ6dgd07iEHgRNKQrD3aKK0C7wXWPid/GzN/HvyqMP9/ELgAnAaWTVlzvqy6wYeViPQAj4bWHiwa8wdw7RTxXRiFW6VkzDMKu1p2rvp6GEVOL8lTMOZHgUU1Ittc9v1PwlJpuYpsDa1dWchmXxSRd+qkYqMUjYmA9S0QyGscP5IZGPiq1nYsm+3xRLZP2d1HgbUdhc7OPV4c74pFfhcYquNjwBE4BtzThMDXobUr6tk4Aj4cD6LIJoLu7FzrqX4YWivFbHa7ByN+FPUVjdE664ekYMxpgZtTCBwPrV3aQoQmTCrO/kzyruoq4Sgin03FUDjjIvAvcEUjB+p592VyORcldM2atvKcOU+UVe/yRFap6lkVOeuJHArC8AvZvXu0mM2uruesAX4+nYDqu2EUdSehHc/108BigZ9jKAjcCMx13wV+Ung+tHa/GrOoDN3uvUnk8qkpCHz/FunvP1MjtN8EtgXW5mqqYKJ9T+31RWNc2C9pRDUVk6SgoQidkNSY9jKMKFwpquuDKPrAAZSy2SfdbzEIhoNy+dTETlUfDqPoUEWQvZ7qyylRGEotwyCOlxQg43nesAMJguC6UqnUDywsqXa3R9GBhERlp3Ui4Mg+lUJgIL0Rqb4SRtHbRWOOAMtdBFRki6oOZaJoayUSRkX2JiKFTRlr33f/C9nsJhHZmaYBgY2prdgNmaK6ORD5oQR9wApPtddrb99JPr+0JLIKkV7gZKy6xx1EifOurnsljg+S0t4Fxltxsosmh5E75Tw4gOfFqG4DHge+jVX3+yLD/sWLH8u+fWP5detu8j2v2xd5QSHTZPfjh1EilvHJt5Xj+ITAkVj1mMB3KnKDqF6vsExE7gTuSOspVUJuWJ10HFeENFMDSStNc/JAUq+mW0GZjk3DkawKNqtDaZXErI7lNSRm72JSQ2L2rmZVErN6Oa1V+eW6nv8HsLDsTPZk+K0AAAAASUVORK5CYII="
            width="22" height="22" title="微博" style="vertical-align: middle; margin-left: .8em;">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAA+pJREFUWEe9V1toHGUU/r5/Zme3sbYVbUGhtZcHURQUQSqi5EEf3Mx2aaCR+mLfvEDxSSoo2lAv2Ce12uKL0gdFGzS2OxsNFOKtWtC0IFSpYOwFL2CktJI28+/Mf2Rms8lms7MzSRrPy87snMv3n9t/DpGVDsnK3DXhFhqUAW4EcAOI62NxwT8AxgEZE4XDtQnrCPp4MYtqpjE5ntxKmJ1CPArByjT++DtxkYIPBGqfdvlzJ5mOABwvfAnATgArMhmey3QJwD7tWs8nyScCcLzwEIBtCzTcKjagXauvna62ABwv/A3A+qtkvKHmjHatDa065wDIe+HfEiXYEhCBcd+1VjerngUg7wVfCXh/VtsCftHgJaQ7ixwhX/uu/cCM3NTTVMI9l0VJvfRMvy7ldkePOc+MZAUwpf/lRmLGHohKDTDH55XtiwNwCVCboxKNAeS9cL8AT7Y9vZj+JK80POBUarEn2hLViwnZf8B3raeIw3KtY5mzAK5rDwD9gAGgUP+docUAAHBBh+pmOpVgK8hPMse+wbi4ENS1iPTSqYYfQvBIRwAJYUj1QIL7p20RH9GphCdA3LUQAK0yJL7z3dxw5soQnIwAnAOxdt4haBUQnNIl6/a4qqpyB8T8mKpTcJ6OF04A6EplTmd4QbvWnhhAJXwVxLPpIricBmBAoF6ruRyNlf0guWV/XlkTKHs1kdsEY8pQ2BJd06S6xe/hL8uHZY2ume8BrMsGICkEYvqVst8LBd2E6QYkT3A0FDMaGHsUZf4bGVg2LGuNNmW/ZL0Vvecr4dNCvJ7BeDTInE9KwoOG5l0l6suEJjIOYBAin/ole6iZx6nKdkC2Q6SUCiJOwnZlSOwGWIZI5+qIa3nmTmg2WPBko8BslQgMeHcCmIH2jSiue94D8uG08mz0gvzQ5Caa/LpJlyOtMgWv9mBI5VK4DZCbZr5LX1Irfh9KvQJjTiUAuAAxb07fhtXwCQoORLyEHAOsPb7LuB80U9fncmMtCEukcqMQaddi4mWkamqD3YXLOpD9EHmIgBbgd0K+RRi84ZcLpwuDst7YwQ606XgEv4GYvX7JrrQ7hD0k9wVFHut8HSvs0kVrb6uCXFXfCbEeI7ADwKpOYRLICIm3dY/9cUJC1//uMJCcgZiDcb5RrSC4GZB7UzN8DgOHIXxHlzjY/GlRI9n8QcSn8PQf7MXjrNVzpoWWciiNZoAcr9w20bP8r4bZ/28sJ06DqlcX+VNiCGZ1tKu6mMggVfCMXyz82urxpV7NEjtlxxDM9sQCltOoX4hUYFlH/CI/65SsqdvxtHDyeu5HaznAMQBjCubopJs7mrVC/gP3pMBPGus09QAAAABJRU5ErkJggg=="
            width="22" height="22" title="支付宝" style="vertical-align: middle; margin-left: .8em;">
          <img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAvFJREFUWEe1l1uIjkEYx3+bKyUUq4RyinCh2CQlwpXNMYQruzmvw41yLJS1RSk5LkqhiEVOyYXDBWHZUKSUzYVSIofCrf5fM9vsmHnf+b59PfVefDPP/J//zDfP8/ynivKsPzAHGAH0BfqY5V+BL8A74BrwKRW2KtFxOTAbmJXofwO4DpzK888jMB9YD0zNA4rMPwAOAVdi67MIHAYaKgzsLzsCrAthxQjcBaYVFNzC3AOm+5ghAueBxQUHt3AXgCUutk9gF7DzPwW3sLsBxSmZS2ANcNQLXgfcBmqApeZL4XcJOAO8NsfuZ8Na4JhLoCfwBBjloQ8BPjhj44HJZkzjdm4wYL+nwGNnzWjgjYf7FpgI/LQnsB3YE9jaIOBjypYzfHoD3wLzO4BGS+A9MDTgtAjQcXbF5gJXAwDtwDARmAncikQogsBC4GIEv1YEjgOrAg4nIuOVnEYzsDKwsFkEdGkmeJO/gB6VRMpY8xmo9uZbRSD0/+t2KwOKtPuBntIuAt+BXl6kZ4FT6SoZ9QPlv2s/ROA30D2Qp8rfIk2FZ7UH+EcEXgJjA5EkOCQ0irJHwCQP7JUIKM8XBKJI+UhUFGHDgReBi90iAnuBrYEoaskziohuNhJSU00isAw4HQm0IkVW5ZCUqroc8akTgXFAK9At4rQZ2FfhSWRtTpA1thccBDaYINJxMlcHngVayrgTErDzzOnGuJcqrSUw0lRE1QOlywFgSuD4Vc1EJKYVleu60P0STkwao80VJI3ANrNQIkRNSr817ppSye337pzS+WFCGe/oMy6BAcAdYIxB1F8iSa12utGMqURLJWVZiqYs7V4gviZU9VPrFAk5yLFcawK2ZCzq1OJDqtgloeolJSttZ81e0liM/cCmyOQ/+iL2LnBJuFidFG0kyElATznfguIm62UkPaid2PQUYAoBv+vpnahLdzNEOO9tqDWqByJSm0hA7wrp/ufGPxjYkkkhYH3rAQnJvDsgwgOBcym39y/8togjtPtXzgAAAABJRU5ErkJggg=="
            width="22" height="22" title="GitHub" style="vertical-align: middle; margin-left: .8em;">
          <!--      <a href="https://gitee.com/oauth/authorize?client_id=3a19b0430710fdd5cf0531bb03a40527a6678d71b831b8722b493dcb4148a645&redirect_uri=http://127.0.0.1:8080/melon/oauth/callback/gitee&response_type=code&state=1">-->
          <a :href="oauthUrl" >
            <img
              src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAtRJREFUWEfFV01rE1EUPWdiQQRbxLoRtM0k06KuRBdulLpUkpQKreBC3BXF/gIFFSz+gKKi24JoC0IzoduKLnThx0q0TjpJ1Z0VaUURbObKTD6YTOcrsSFZhXc/zpn37rnvPiLmb05V+3bKjhwooxSoQvQT2GuHC/CdgjUhTAgX/nAzP2Ga63FSM8qpMKAdkgSmCFwQoC/K37YTWBfgESuYyawaH8JiQgkUUtptEUwB6I0D7OOzQWIms2JcD4oPJJBXtTkC420CN4UJMJ8zjQm/XL4ECqpWEmBwO8DrOQiUM6aR9ObcQkBXtW8A+rcT3JVrLWsa+9y5mwjoqvYcwMkOgdfTvsiaxinXzlT/1gruWiA4OUvL0pUEXp4tFr/GJVlIpY6JKK+bvpqYrhemswM1qb0KqnaKTGRKxfm4oF4/XdXEs7bBCk7YEnUI6Kp2D8Bl3yqldTyzsvKmXfBafi8Be/l+1jSucGF4eLfyt7IKcI8XRAQPcyVj0r1eSKbHK8SRuIQS4IAAl7b6yw+rJzFAPTU0BpGnfglF5GKuVJyt2/SktgRiJC54pB95jnoy/RjkeT/nTSuRHCt/LDvbOJgegcKlyKStOIg8oZ7U3oI46heXNY2GTBfU9E0FvNFK/khfwTvmVe0zgQOtErAgtxQLz6JAmOCQCB74HjHwhbqq/QKwq1UCsOR0tlyMJBCiAtv0u/sEOnEE3p3xaUTOhotzBJ0oQs/xBBGAXYRhMmxXBVaP0ju6vPyz0T+2tuKqyZFhSCNqj4DczZrFq3XwfGroDEUWfdViN6KwVuwmYDciSwnvgrYsvecf3D9qrbgmE9/LyE0gSu9+9sV0+nDF4vuA2OplZBuDruP/IeA3B7iINF/HDonqBNw0kJCYlIp8aunrFe4XyEGCd4Li6B1IXNXavZHMRaJ7Q2mdRFfH8oZ+u/kwaexEN59mDRLdfJy6ZdSp5/k/afF2RPisToMAAAAASUVORK5CYII="
              width="22" height="22" title="Gitee" style="vertical-align: middle; margin-left: .8em;">
          </a>
        </div>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2023-???? melon All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import {getCodeImg, getGiteeCode} from "@/api/login";
import Cookies from "js-cookie";
import {decrypt, encrypt} from '@/utils/jsencrypt'

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      oauthUrl: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: "",
        giteeAuthUrl: ""
      },
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "请输入您的账号"}
        ],
        password: [
          {required: true, trigger: "blur", message: "请输入您的密码"}
        ],
        code: [{required: true, trigger: "change", message: "请输入验证码"}]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: false,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        console.info("watch handler")
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    console.info("created")
    this.getCode();
    this.getOauthUrl();
    this.getCookie();
    this.getOauth2Toekn();
  },
  activated() {
    console.info("activated")
    this.getOauth2Toekn();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getOauthUrl() {
      getGiteeCode().then(res => {
        //console.log("获取到oauth的地址为：", res);
        this.oauthUrl = res.msg;
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      const oauth2Toekn = Cookies.get('oauth2Toekn')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    getOauth2Toekn() {
      const token = Cookies.get('oauth2Token');
      console.log("oauth token data 1 "+token)
      if (token != undefined) {
        this.handlerOauth(token);
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, {expires: 30});
            Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
            Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({path: this.redirect || "/"}).catch(() => {
            });
          }).catch(() => {
          });
        }
      });
    },
    handlerOauth(token) {
      this.$store.dispatch("Oauth", token).then(() => {
        console.log("handlerOauth...")
        this.$router.push({path: this.redirect || "/"}).catch(() => {
        });
      }).catch(() => {
      });
    }

  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }

  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: .8em;
  }

}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 38px;
  float: right;
}

img {
  cursor: pointer;
  vertical-align: middle;
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}

.login-code-img {
  height: 38px;
}
</style>
