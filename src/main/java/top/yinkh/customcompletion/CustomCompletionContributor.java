package top.yinkh.customcompletion;


import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
                        String customCompletion = PropertiesComponent.getInstance().getValue("custom_completion");
                        if (StringUtils.isNotEmpty(customCompletion)) {
                            List<String> completionList = Arrays.stream(customCompletion.split(";")).toList();
                            for (String completion : completionList) {
                                resultSet.addElement(LookupElementBuilder.create(completion).withTypeText("custom"));
                            }
                        }
                    }
                }
        );
    }

}