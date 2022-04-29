package control;

import common.ValueCommandRecipient;

import java.util.List;

public class ValueControl {
    private final List<ValueCommandRecipient> recipients;

    public ValueControl(List<ValueCommandRecipient> recipients) {
        this.recipients = recipients;
    }

    public void execute(long value) {
        for (final ValueCommandRecipient recipient : recipients) {
            recipient.execute(value);
        }
    }
}
