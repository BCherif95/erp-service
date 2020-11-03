package com.tuwindi.erp.erpservice.utils;

public class Enumeration {
    public enum TASK_STATE {
        TODO("Non commence"),
        IN_PROGRESS("Commence"),
        DONE("Termine"),
        PENDING("En attente");

        private final String desc;

        TASK_STATE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum TASK_PRIORITY {
        LOW("Faible"),
        MEDIUM("Moyenne"),
        HIGH("Haute"),
        URGENT("Urgente");

        private final String desc;

        TASK_PRIORITY(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum TYPE_UNITY {
        UNITY1("Unité 1"),
        UNITY2("Unité 2");

        private final String desc;

        TYPE_UNITY(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }

    public enum LINE_STATE {
        PENDING("En attente"),
        IN_FINANCING("En financement"),
        CLOSE("Solder");

        private final String desc;

        LINE_STATE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum METHOD_OF_PAYMENT {
        CHECK("Chèque"),
        TRANSFER("Virement"),
        CASH("Espèce");

        private final String desc;

        METHOD_OF_PAYMENT(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum DEMAND_STATE {
        APPROVED("A approuver"),
        IN_VALIDATION("En validation"),
        TO_CONFIRM("Confirmer"),
        REFUSE("Réfuser");

        private final String desc;

        DEMAND_STATE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum SPEND_STATE {
        AWAITING_APPROVAL("En attente d'approbation"),
        APPROVED("Approuvé"),
        AWAITING_VALIDATION("En attente de validation"),
        VALIDATION("Validé");

        private final String desc;

        SPEND_STATE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
