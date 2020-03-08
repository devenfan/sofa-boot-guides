package com.alipay.sofa.boot.examples.lucene.domain;

import java.io.Serializable;

/**
 * DemoDO
 *
 * @author Deven
 * @version : DemoDO, v 0.1 2020-03-08 22:38 Deven Exp$
 */
public class DemoDO implements Serializable {

    private static final long serialVersionUID = 3567639613434747320L;

    private String id;

    private String firstName;

    private String lastName;

    private String website;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
