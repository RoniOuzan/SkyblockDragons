package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class MessageModifier {
    private final String text;
    private final List<Entry<TextMessage.Modifier, String>> modifiers;

    public MessageModifier(String text, Entry<TextMessage.Modifier, String>... modifiers) {
        this.text = text;
        this.modifiers = Arrays.asList(modifiers);
    }

    public void applyModifiers(TextMessage textMessage) {
        TextMessage.TextBuilder builder = textMessage.append(this.text);
        for (Entry<TextMessage.Modifier, String> modifier : modifiers) {
            switch (modifier.getA()) {
                case HOVER_AS_TOOLTIP:
                    builder.setHoverAsTooltip(modifier.getB());
                    break;
                case URL:
                    builder.setClickAsURL(modifier.getB());
                    break;
                case SUGGEST_MESSAGE:
                    builder.setClickAsSuggestCmd(modifier.getB());
                    break;
                case MESSAGE:
                    builder.setClickAsExecuteCmd(modifier.getB());
                    break;
            }
        }
    }
}
