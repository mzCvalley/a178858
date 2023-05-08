package com.fyp.a178858.entity;

import com.fyp.a178858.enums.DayTypeEnum;
import com.fyp.a178858.enums.OtRequestEnum;
import com.fyp.a178858.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FYP_OT")
@Getter
@Setter
@ToString
public class Ot extends BaseEntity {

    @Column(name = "OT_DATE")
    private LocalDate otDate;

    @Column(name = "DURATION", scale = 1)
    private BigDecimal duration;

    @Column(name = "JOB_DESC")
    private String jobDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_STATUS")
    private OtRequestEnum requestStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_BY")
    private UserTypeEnum userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "DAY_TYPE")
    private DayTypeEnum dayType;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEE_USER_ID", referencedColumnName = "ID")
    private User user;


    public static final class builder {
        private Long id;
        private LocalDate otDate;
        private BigDecimal duration;
        private String jobDesc;
        private OtRequestEnum requestStatus;
        private UserTypeEnum userType;
        private DayTypeEnum dayType;
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

        public builder withOtDate(LocalDate otDate) {
            this.otDate = otDate;
            return this;
        }

        public builder withDuration(BigDecimal duration) {
            this.duration = duration;
            return this;
        }

        public builder withJobDesc(String jobDesc) {
            this.jobDesc = jobDesc;
            return this;
        }

        public builder withRequestStatus(OtRequestEnum requestStatus) {
            this.requestStatus = requestStatus;
            return this;
        }

        public builder withUserType(UserTypeEnum userType) {
            this.userType = userType;
            return this;
        }

        public builder withDayType(DayTypeEnum dayType) {
            this.dayType = dayType;
            return this;
        }

        public builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Ot build() {
            Ot ot = new Ot();
            ot.setId(id);
            ot.setOtDate(otDate);
            ot.setDuration(duration);
            ot.setJobDesc(jobDesc);
            ot.setRequestStatus(requestStatus);
            ot.setUserType(userType);
            ot.setDayType(dayType);
            ot.setUser(user);
            return ot;
        }
    }
}
