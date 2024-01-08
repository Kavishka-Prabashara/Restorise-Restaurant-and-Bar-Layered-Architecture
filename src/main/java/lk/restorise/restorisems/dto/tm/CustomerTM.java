package lk.restorise.restorisems.dto.tm;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CustomerTM {
        private final SimpleStringProperty customerId;
        private final SimpleStringProperty customerName;
        private final SimpleIntegerProperty mobileNo;
        private final SimpleStringProperty orderID;
        private final SimpleObjectProperty<Date> orderDate;

        public CustomerTM(String customerId, String customerName, int mobileNo, String orderID, Date orderDate) {
                this.customerId = new SimpleStringProperty(customerId);
                this.customerName = new SimpleStringProperty(customerName);
                this.mobileNo = new SimpleIntegerProperty(mobileNo);
                this.orderID = new SimpleStringProperty(orderID);
                this.orderDate = new SimpleObjectProperty<>(orderDate);
        }

        public String getCustomerID() {
                return customerId.get();
        }

        public SimpleStringProperty customerIdProperty() {
                return customerId;
        }

        public String getCustomerName() {
                return customerName.get();
        }

        public SimpleStringProperty customerNameProperty() {
                return customerName;
        }

        public int getCustomerMobile() {
                return mobileNo.get();
        }

        public SimpleIntegerProperty customerMobileNoProperty() {
                return mobileNo;
        }

        public String getOrderID() {
                return orderID.get();
        }

        public SimpleStringProperty orderIDProperty() {
                return orderID;
        }

        public Date getOrderDate() {
                return orderDate.get();
        }

        public SimpleObjectProperty<Date> orderDateProperty() {
                return orderDate;
        }
}
