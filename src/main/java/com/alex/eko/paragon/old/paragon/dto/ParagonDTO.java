package com.alex.eko.paragon.old.paragon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParagonDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long userId;

    String taxpayerName;

    String saleAddress;

    String nip;

    Long sequentialPrintNumber;

    LocalDateTime salesDate;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxA;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxB;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxC;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxA_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxB_PTU;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double salesTaxC_PTU;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double sumPTU;

    String currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Double totalDiscount;

    Double grossPrice;

    Long consecutiveNumberFiscalReceipt;

    String cashRegisterNumber;

    String cashierIdentification;

    List<ProductDTO> productDTOs;


    @Override
    public String toString() {
        return "ParagonDTO{" +
                "uuid=" + uuid +
                ", userId=" + userId +
                ", taxpayerName='" + taxpayerName + '\'' +
                ", saleAddress='" + saleAddress + '\'' +
                ", nip='" + nip + '\'' +
                ", sequentialPrintNumber=" + sequentialPrintNumber +
                ", salesDate=" + salesDate +
                ", salesTaxA=" + salesTaxA +
                ", salesTaxB=" + salesTaxB +
                ", salesTaxC=" + salesTaxC +
                ", salesTaxA_PTU=" + salesTaxA_PTU +
                ", salesTaxB_PTU=" + salesTaxB_PTU +
                ", salesTaxC_PTU=" + salesTaxC_PTU +
                ", sumPTU=" + sumPTU +
                ", currency='" + currency + '\'' +
                ", totalDiscount=" + totalDiscount +
                ", grossPrice=" + grossPrice +
                ", consecutiveNumberFiscalReceipt=" + consecutiveNumberFiscalReceipt +
                ", cashRegisterNumber='" + cashRegisterNumber + '\'' +
                ", cashierIdentification='" + cashierIdentification + '\'' +
                ", productDTOs=" + productDTOs +
                '}';
    }
}
