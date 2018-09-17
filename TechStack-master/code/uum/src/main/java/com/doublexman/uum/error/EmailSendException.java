package com.doublexman.uum.error;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EmailSendException extends AbstractThrowableProblem {

    public EmailSendException() {
        super(ErrorConstants.EMAIL_SEND_ERROR_TYPE, "通知邮件发送失败", Status.INTERNAL_SERVER_ERROR);
    }
}
