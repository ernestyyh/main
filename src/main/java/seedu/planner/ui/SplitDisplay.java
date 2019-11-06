//package seedu.planner.ui;
//
//import java.util.logging.Logger;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.layout.Region;
//import seedu.planner.commons.core.LogsCenter;
//import seedu.planner.model.contact.Contact;
//import seedu.planner.model.accommodation.Accommodation;
//import seedu.planner.model.activity.Activity;
//
///**
// * Panel containing the list of persons.
// */
//public class SplitDisplay extends UiPart<Region> {
//    private static final String FXML = "SplitDisplay.fxml";
//    private final Logger logger = LogsCenter.getLogger(SplitDisplay.class);
//
//    @FXML
//    private Tab accommodationTab;
//
//    @FXML
//    private Tab activityTab;
//
//    @FXML
//    private Tab contactTab;
//
//    @FXML
//    private Tab infoListTab;
//
//    @FXML
//    private Tab itineraryTab;
//
//    @FXML
//    private TabPane infoTabPane;
//
//    @FXML
//    private TabPane mainTabPane;
//
//    /**
//     * Split display containing the list of information.
//     */
//    public SplitDisplay(ObservableList<Accommodation> accommodationList, ObservableList<Activity> activityList,
//                        ObservableList<Contact> contactList) {
//        super(FXML);
//
//        accommodationTab.setContent((new AccommodationListPanel(accommodationList)).getRoot());
//        activityTab.setContent((new ActivityListPanel(activityList)).getRoot());
//        contactTab.setContent((new ContactListPanel(contactList)).getRoot());
//        infoListTab.setContent((new InfoListPanel(contactList)).getRoot());
//    }
//
//}