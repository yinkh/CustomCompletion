package top.yinkh.customcompletion;


import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author yinkanghong
 * @date 2023-05-19
 * @desc
 */
public class CustomCompletionContributor extends CompletionContributor {

    public CustomCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        List<String> completionList = PropertiesComponent.getInstance().getList("custom_completion");
                        if (CollectionUtils.isNotEmpty(completionList)) {
                            for (String completion : completionList) {
                                resultSet.addElement(LookupElementBuilder.create(completion)
                                        .withIcon(MyIcons.Action)
                                        .withTypeText("custom"));
                            }
                        }
                    }
                }
        );
    }

}