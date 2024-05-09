module maranich_nkdeb.maranich_nkdeb {
    requires javafx.controls;
    requires javafx.fxml;


    opens maranich_nkdeb.TP_POO to javafx.fxml;
    exports maranich_nkdeb.TP_POO;
    exports maranich_nkdeb.TP_POO.data;
    opens maranich_nkdeb.TP_POO.data to javafx.fxml;
}