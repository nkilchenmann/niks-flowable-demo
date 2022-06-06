package com.example.flowabledemo.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class OnboardingEligibilityCheckProcessModel implements Serializable {
    private List<Partner> linkedPartners;

    public OnboardingEligibilityCheckProcessModel(List<Partner> partnerList) {
        this.linkedPartners = partnerList;
    }
}
