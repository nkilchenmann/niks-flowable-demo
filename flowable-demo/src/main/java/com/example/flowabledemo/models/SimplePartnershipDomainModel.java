package com.example.flowabledemo.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SimplePartnershipDomainModel implements Serializable {
    private List<Partner> linkedPartners;

    public SimplePartnershipDomainModel(List<Partner> partnerList) {
        this.linkedPartners = partnerList;
    }

    @Override
    public String toString() {
        return "SimplePartnershipDomainModel{" +
                "linkedPartners=" + linkedPartners +
                '}';
    }
}
