package com.company.project.model;

import java.math.BigDecimal;
import javax.persistence.*;

public class Ac11 {
    @Id
    @Column(name = "AAZ304")
    private BigDecimal aaz304;

    @Column(name = "AAC001")
    private Long aac001;

    @Column(name = "AAE008")
    private String aae008;

    @Column(name = "AAE009")
    private String aae009;

    @Column(name = "AAE010")
    private String aae010;

    @Column(name = "AAA082")
    private String aaa082;

    @Column(name = "PRSENO")
    private Long prseno;

    /**
     * @return AAZ304
     */
    public BigDecimal getAaz304() {
        return aaz304;
    }

    /**
     * @param aaz304
     */
    public void setAaz304(BigDecimal aaz304) {
        this.aaz304 = aaz304;
    }

    /**
     * @return AAC001
     */
    public Long getAac001() {
        return aac001;
    }

    /**
     * @param aac001
     */
    public void setAac001(Long aac001) {
        this.aac001 = aac001;
    }

    /**
     * @return AAE008
     */
    public String getAae008() {
        return aae008;
    }

    /**
     * @param aae008
     */
    public void setAae008(String aae008) {
        this.aae008 = aae008;
    }

    /**
     * @return AAE009
     */
    public String getAae009() {
        return aae009;
    }

    /**
     * @param aae009
     */
    public void setAae009(String aae009) {
        this.aae009 = aae009;
    }

    /**
     * @return AAE010
     */
    public String getAae010() {
        return aae010;
    }

    /**
     * @param aae010
     */
    public void setAae010(String aae010) {
        this.aae010 = aae010;
    }

    /**
     * @return AAA082
     */
    public String getAaa082() {
        return aaa082;
    }

    /**
     * @param aaa082
     */
    public void setAaa082(String aaa082) {
        this.aaa082 = aaa082;
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