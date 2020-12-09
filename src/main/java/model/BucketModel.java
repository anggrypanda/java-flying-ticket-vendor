package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketModel {
    private float price;
    private int totalPerType;
    private int availablePerType;
}
