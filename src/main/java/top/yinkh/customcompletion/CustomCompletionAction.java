package top.yinkh.customcompletion;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author yinkanghong
 * @date 2023-05-19
 * @desc
 */

public class CustomCompletionAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        new CompletionDialogWrapper().showAndGet();
    }
}
