package com.company.project.model;

import java.math.BigDecimal;
import javax.persistence.*;

public class Aa10 {
    @Id
    @Column(name = "AAZ093")
    private BigDecimal aaz093;

    @Column(name = "AAA100")
    private String aaa100;

    @Column(name = "AAA102")
    private String aaa102;

    @Column(name = "AAA103")
    private String aaa103;

    @Column(name = "AAA105")
    private String aaa105;

    @Column(name = "AAE100")
    private String aae100;

    @Column(name = "AAA104")
    private String aaa104;

    @Column(name = "PRSENO")
    private Long prseno;

    /**
     * @return AAZ093
     */
    public BigDecimal getAaz093() {
        return aaz093;
    }

    /**
     * @param aaz093
     */
    public void setAaz093(BigDecimal aaz093) {
        this.aaz093 = aaz093;
    }

    /**
     * @return AAA100
     */
    public String getAaa100() {
        return aaa100;
    }

    /**
     * @param aaa100
     */
    public void setAaa100(String aaa100) {
        this.aaa100 = aaa100;
    }

    /**
     * @return AAA102
     */
    public String getAaa102() {
        return aaa102;
    }

    /**
     * @param aaa102
     */
    public void setAaa102(String aaa102) {
        this.aaa102 = aaa102;
    }

    /**
     * @return AAA103
     */
    public String getAaa103() {
        return aaa103;
    }

    /**
     * @param aaa103
     */
    public void setAaa103(String aaa103) {
        this.aaa103 = aaa103;
    }

    /**
     * @return AAA105
     */
    public String getAaa105() {
        return aaa105;
    }

    /**
     * @param aaa105
     */
    public void setAaa105(String aaa105) {
        this.aaa105 = aaa105;
    }

    /**
     * @return AAE100
     */
    public String getAae100() {
        return aae100;
    }

    /**
     * @param aae100
     */
    public void setAae100(String aae100) {
        this.aae100 = aae100;
    }

    /**
     * @return AAA104
     */
    public String getAaa104() {
        return aaa104;
    }

    /**
     * @param aaa104
     */
    public void setAaa104(String aaa104) {
        this.aaa104 = aaa104;
    }

    /**
     * @return PRSENO
     */
    public Long getPrseno() {
        return prseno;
    }

    /**
     * @param prseno
     */
    public void setPrseno(Long prseno) {
        this.prseno = prseno;
    }
}