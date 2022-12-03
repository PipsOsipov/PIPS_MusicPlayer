module Music_Player {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
