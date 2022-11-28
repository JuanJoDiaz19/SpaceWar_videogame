
module com.example.tank_battle {
    requires javafx.controls;
    requires javafx.fxml;
    //requires jfxrt;
    //requires rt;


    opens com.example.tank_battle to javafx.fxml;
    exports com.example.tank_battle;


}

