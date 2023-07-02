package fun.sssdnsy.web.editor;

import org.activiti.engine.ActivitiException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;


@Controller
public class EditorController {


    /**
     * 流程图编辑器
     *
     * @return
     */
    @GetMapping("editor")
    public String editor() {
        return "modeler";
    }

    /**
     * 获取流程图编辑器的汉化文件
     *
     * @return
     */
    @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return org.apache.commons.io.IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}
