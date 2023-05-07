package com.fyp.a178858.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "FYP_WORK_CHECK_IN")
@Getter
@Setter
@ToString
public class WorkCheckIn extends BaseEntity {
    @Column(name = "TOTAL_WORK_HOURS", scale = 2)
    private BigInteger totalWorkHours;

    @Column(name = "CLOCK_IN_TIME")
    private Instant clockInTime;

    @Column(name = "CLOCK_OUT_TIME")
    private Instant clockOutTime;

    @Column(name = "LATE_IN")
    private Boolean lateIn;

    @Column(name = "LATE_OUT")
    private Boolean lateOut;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEE_USER_ID", referencedColumnName = "ID")
    private User user;


    public static final class builder {
        private Long id;
        private BigInteger totalWorkHours;
        private Instant clockInTime;
        private Instant clockOutTime;
        private Boolean lateIn;
        private Boolean lateOut;
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

        public builder withTotalWorkHours(BigInteger totalWorkHours) {
            this.totalWorkHours = totalWorkHours;
            return this;
        }

        public builder withClockInTime(Instant clockInTime) {
            this.clockInTime = clockInTime;
            return this;
        }

        public builder withClockOutTime(Instant clockOutTime) {
            this.clockOutTime = clockOutTime;
            return this;
        }

        public builder withLateIn(Boolean lateIn) {
            this.lateIn = lateIn;
            return this;
        }

        public builder withLateOut(Boolean lateOut) {
            this.lateOut = lateOut;
            return this;
        }

        public builder withUser(User user) {
            this.user = user;
            return this;
        }

        public WorkCheckIn build() {
            WorkCheckIn workCheckIn = new WorkCheckIn();
            workCheckIn.setId(id);
            workCheckIn.setTotalWorkHours(totalWorkHours);
            workCheckIn.setClockInTime(clockInTime);
            workCheckIn.setClockOutTime(clockOutTime);
            workCheckIn.setLateIn(lateIn);
            workCheckIn.setLateOut(lateOut);
            workCheckIn.setUser(user);
            return workCheckIn;
        }
    }
}
