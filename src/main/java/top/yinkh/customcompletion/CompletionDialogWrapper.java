package top.yinkh.customcompletion;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yinkanghong
 * @date 2023-05-19
 * @desc
 */

public class CompletionDialogWrapper extends DialogWrapper {
    private JTextArea text;

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

        List<String> completionList = PropertiesComponent.getInstance().getList("custom_completion");
        text = new JTextArea(18,100);
        // 自动换行
        text.setLineWrap(true);
        if (CollectionUtils.isEmpty(completionList)) {
            text.setText("");
        } else {
            text.setText(StringUtils.join(completionList, ";"));
        }

        JBScrollPane areaScrollPane = new JBScrollPane(text);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialogPanel.add(areaScrollPane, BorderLayout.CENTER);

        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        if (getOKAction().isEnabled()) {
            if (StringUtils.isNotEmpty(text.getText())) {
                List<String> completionList = Arrays.stream(text.getText().split(";")).toList();
                PropertiesComponent.getInstance().setList("custom_completion", completionList);
            } else {
                PropertiesComponent.getInstance().setList("custom_completion", new ArrayList<>());
            }
            close(OK_EXIT_CODE);
        }
    }
}
