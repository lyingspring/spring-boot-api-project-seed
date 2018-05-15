package com.company.project.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "ADE4_TEMP")
public class Ade4Temp {
    @Id
    @Column(name = "EAD184")
    private String ead184;

    @Column(name = "EAD186")
    private BigDecimal ead186;

    /**
     * @return EAD184
     */
    public String getEad184() {
        return ead184;
    }

    /**
     * @param ead184
     */
    public void setEad184(String ead184) {
        this.ead184 = ead184;
    }

    /**
     * @return EAD186
     */
    public BigDecimal getEad186() {
        return ead186;
    }

    /**
     * @param ead186
     */
    public void setEad186(BigDecimal ead186) {
        this.ead186 = ead186;
    }
}