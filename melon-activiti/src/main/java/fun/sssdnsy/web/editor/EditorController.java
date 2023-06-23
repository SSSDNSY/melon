package fun.sssdnsy.web.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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

}
