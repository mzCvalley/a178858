package com.fyp.a178858.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FYP_DAILY_SALARY")
@Getter
@Setter
@ToString
public class DailySalary extends BaseEntity {
    @Column(name = "RECORD_DATE")
    private LocalDate recordDate;

    @Column(name = "NORMAL_PAY_AMOUNT", scale = 2)
    private BigDecimal normalPayAmount;

    @Column(name = "OT_PAY_AMOUNT", scale = 2)
    private BigDecimal otPayAmount;

    @Column(name = "TOTAL_PAY_AMOUNT", scale = 2)
    private BigDecimal totalPayAmount;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEE_USER_ID", referencedColumnName = "ID")
    private User user;


    public static final class builder {
        private Long id;
        private LocalDate recordDate;
        private BigDecimal normalPayAmount;
        private BigDecimal otPayAmount;
        private BigDecimal totalPayAmount;
        private User user;

        private builder() {
        }

        public static builder instance() {
            return new builder();
        }

        public builder withId(Long id) {
            this.id = id;
            return this;
        }

        public builder withRecordDate(LocalDate recordDate) {
            this.recordDate = recordDate;
            return this;
        }

        public builder withNormalPayAmount(BigDecimal normalPayAmount) {
            this.normalPayAmount = normalPayAmount;
            return this;
        }

        public builder withOtPayAmount(BigDecimal otPayAmount) {
            this.otPayAmount = otPayAmount;
            return this;
        }

        public builder withTotalPayAmount(BigDecimal totalPayAmount) {
            this.totalPayAmount = totalPayAmount;
            return this;
        }

        public builder withUser(User user) {
            this.user = user;
            return this;
        }

        public DailySalary build() {
            DailySalary dailySalary = new DailySalary();
            dailySalary.setId(id);
            dailySalary.setRecordDate(recordDate);
            dailySalary.setNormalPayAmount(normalPayAmount);
            dailySalary.setOtPayAmount(otPayAmount);
            dailySalary.setTotalPayAmount(totalPayAmount);
            dailySalary.setUser(user);
            return dailySalary;
        }
    }
}
