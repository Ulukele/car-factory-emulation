package control;

import common.ValueCommandRecipient;

import java.util.List;

public class ValueControl {
    private final List<ValueCommandRecipient> recipients;
    private final ValueCommandRecipient recipient;

    public ValueControl(List<ValueCommandRecipient> recipients) {
        this.recipients = recipients;
        this.recipient = null;
    }
    public ValueControl(ValueCommandRecipient recipient) {
        this.recipients = null;
        this.recipient = recipient;
    }

    public void execute(long value) {
        if (recipient != null) {
            recipient.execute(value);
            return;
        }

        for (final ValueCommandRecipient recipient : recipients) {
            recipient.execute(value);
        }
    }
}
