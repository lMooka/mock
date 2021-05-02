package br.com.guiabolso.mock.entity;

import java.util.Date;

public class MockTransactionDate extends MockTransaction {
    private Date dataCompleta;

    public Date getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(Date dataCompleta) {
        this.dataCompleta = dataCompleta;
    }
}
