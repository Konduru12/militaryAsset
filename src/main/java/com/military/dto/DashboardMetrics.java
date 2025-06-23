package com.military.dto;
import lombok.Data;

@Data
public class DashboardMetrics {
    private int openingBalance;
    private int closingBalance;
    private int netMovement;
    private int purchases;
    private int transfersIn;
    private int transfersOut;
    private int assigned;
    private int expended;
}
