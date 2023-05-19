package top.yinkh.customcompletion;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;


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
            PropertiesComponent.getInstance().setValue("custom_completion", text.getText());
            close(OK_EXIT_CODE);
        }
    }
}
