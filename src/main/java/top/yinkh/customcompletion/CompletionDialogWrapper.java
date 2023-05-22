package top.yinkh.customcompletion;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

/**
 * @author yinkanghong
 * @date 2023-05-19
 * @desc
 */

public class CompletionDialogWrapper extends DialogWrapper {
    private JTextField text;

    public CompletionDialogWrapper() {
        super(true);
        setTitle("Custom Completion");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        label.setText("split by ';' eg: apple;orange");
        dialogPanel.add(label, BorderLayout.NORTH);

        String customCompletion = PropertiesComponent.getInstance().getValue("custom_completion");
        text = new JTextField();
        text.setText(customCompletion);
        text.setPreferredSize(new Dimension(500, 250));
        dialogPanel.add(text, BorderLayout.CENTER);

        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        if (getOKAction().isEnabled()) {
            if (StringUtils.isNotEmpty(text.getText())) {
                List<String> completionList = Arrays.stream(text.getText().split(";")).toList();
                PropertiesComponent.getInstance().setList("custom_completion", completionList);
            }
            close(OK_EXIT_CODE);
        }
    }
}
