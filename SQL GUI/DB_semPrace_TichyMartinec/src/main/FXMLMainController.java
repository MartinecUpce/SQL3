/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.FakultaManager;
import db.KatedraManager;
import db.MistnostManager;
import entity.Fakulta;
import entity.Katedra;
import entity.Obor;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import db.OborManager;
import db.PlanManager;
import db.PredmetManager;
import db.RozvrhovaAktivitaManager;
import db.UserManager;
import db.VyucujiciManager;
import entity.Mistnost;
import entity.Obrazek;
import entity.Plan;
import entity.Predmet;
import entity.RozvrhovaAktivita;
import entity.Vyucujici;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import libs.AlertWindow;
import libs.Rok;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author ROR
 */
public class FXMLMainController implements Initializable {

    @FXML
    private TableView<Vyucujici> tableVyucujici;
    @FXML
    private TableColumn<Vyucujici, Integer> colVyucId;
    @FXML
    private TableColumn<Vyucujici, String> colVyucJmeno;
    @FXML
    private TableColumn<Vyucujici, String> colVyucPrijmeni;
    @FXML
    private TableColumn<Vyucujici, String> colVyucEmail;
    @FXML
    private TableColumn<Vyucujici, String> colVyucTelefon;
    @FXML
    private TableColumn<Vyucujici, String> colVyucKatedra;
    @FXML
    private Button buttonVyucujiciSmazat;
    @FXML
    private TextField textVyucujiciJmeno;
    @FXML
    private TextField textVyucujiciPrijmeni;
    @FXML
    private TextField textVyucujiciEmail;
    @FXML
    private TextField textVyucujiciTelefon;
    @FXML
    private ComboBox<Katedra> comboVyucujiciKatedra;
    @FXML
    private Button buttonVyucujiciVytvorit;
    @FXML
    private Button buttonVyucujiciUpravit;
    @FXML
    private ComboBox<Vyucujici> comboSelectRozvrh;
    @FXML
    private TableView<RozvrhovaAktivita> tableRozvrh;
    @FXML
    private TableColumn<?, ?> colRozvrhId;
    @FXML
    private TableColumn<?, ?> colRozvrhTyden;
    @FXML
    private TableColumn<?, ?> colRozvrhOd;
    @FXML
    private TableColumn<?, ?> colRozvrhDo;
    @FXML
    private TableColumn<?, ?> colRozvrhSchvaleno;
    @FXML
    private TableColumn<?, ?> colRozvrhPredmet;
    @FXML
    private TableColumn<?, ?> colRozvrhDen;
    @FXML
    private TableColumn<?, ?> colRozvrhMistnost;
    @FXML
    private TableColumn<?, ?> colRozvrhVyucujici;
    @FXML
    private Button buttonRozvrhSmazat;
    @FXML
    private RadioButton radioRozvrhVsechny;
    @FXML
    private ToggleGroup tyden;
    @FXML
    private RadioButton radioRozvrhSudy;
    @FXML
    private RadioButton radioRozvrhLichy;
    @FXML
    private TextField textRozvrhOd;
    @FXML
    private TextField textRozvrhDo;
    @FXML
    private CheckBox checkRozvrhSchvaleno;
    @FXML
    private ComboBox<Predmet> comboRozvrhPredmet;
    @FXML
    private ComboBox<String> comboRozvrhDen;
    @FXML
    private ComboBox<Mistnost> comboRozvrhMistnost;
    @FXML
    private Button buttonRozvrhVytvorit;
    @FXML
    private Button buttonRozvrhUpravit;
    @FXML
    private TableView<Predmet> tablePredmet;
    @FXML
    private TableColumn<?, ?> colPredmetId;
    @FXML
    private TableColumn<?, ?> colPredmetNazev;
    @FXML
    private TableColumn<?, ?> colPrdemtZpusob;
    @FXML
    private Button buttonPredmetSmazat;
    @FXML
    private Button buttonPredmetVytvorit;
    private Button rozvrhovaAktivita;
    @FXML
    private TextField textPredmetNazev;
    @FXML
    private ComboBox<String> comboPredmetZakonceni;
    @FXML
    private TableView<Fakulta> tableFakulta;
    @FXML
    private TableColumn<Fakulta, Integer> colFakultaId;
    @FXML
    private TableColumn<Fakulta, String> colFakultaNazev;
    @FXML
    private TableColumn<Fakulta, String> colFakultaZkratka;
    @FXML
    private Button buttonFakultaSmazat;
    @FXML
    private TextField textFakultaNazev;
    @FXML
    private TextField textFakultaZkratka;
    @FXML
    private Button buttonFakultaVytvorit;
    @FXML
    private Button buttonFakultaUpravit;
    @FXML
    private TableColumn<Katedra, Integer> colKatedraId;
    @FXML
    private TableColumn<Katedra, String> colKatedraNazev;
    @FXML
    private TableColumn<Katedra, String> colKatedraZkratka;
    @FXML
    private Button buttonKatedraSmazat;
    @FXML
    private TextField textKatedraNazev;
    @FXML
    private TextField textKatedraZkratka;
    @FXML
    private ComboBox<Fakulta> comboKatedraFakulta;
    @FXML
    private Button buttonKatedraVytvorit;
    @FXML
    private Button buttonKatedraUpravit;
    @FXML
    private TableView<Mistnost> tableMistnost;
    @FXML
    private TableColumn<Mistnost, Integer> colMistnostId;
    @FXML
    private TableColumn<Mistnost, String> colMistnostOznaceni;
    @FXML
    private TableColumn<Mistnost, Integer> colMisnostKapacita;
    @FXML
    private Button buttonMistnostSmazat;
    @FXML
    private TextField textMistnostOznacecni;
    @FXML
    private TextField textMistnostKapacita;
    @FXML
    private Button buttonMistnostVytvorit;
    @FXML
    private Button buttonMistnostUpravit;
    @FXML
    private TableView<Obor> tableObory;
    @FXML
    private TableColumn<Obor, Integer> colOborId;
    @FXML
    private TableColumn<Obor, String> colOborNazev;
    @FXML
    private TableColumn<Obor, Integer> colOborOdhad;
    @FXML
    private Button buttonOborSmazat;
    @FXML
    private Button buttonOborVytvorit;
    @FXML
    private Button buttonOborUpravit;
    @FXML
    private TextField textOborNazev;
    @FXML
    private TextField textOborOdhad;
    @FXML
    private Button buttonPlanAddVyucujici;
    @FXML
    private TableColumn<?, ?> colPlanId;
    @FXML
    private TableColumn<?, ?> colPlanJmeno;
    @FXML
    private TableColumn<?, ?> colPlanObor;
    @FXML
    private Button buttonPlanSmazat;
    @FXML
    private TextField textPlanJmeno;
    @FXML
    private ComboBox<Obor> comboPlanObor;
    @FXML
    private Button buttonPlanPridat;
    @FXML
    private Button buttonPlanUpravit;

    private User loggedUser;
    private Connection conn;
    @FXML
    private ComboBox<Rok> comboRozvrhRok;
    @FXML
    private Button buttonPredmet;
    @FXML
    private ComboBox<Vyucujici> comboPredmetVyucujici;
    @FXML
    private ListView<Vyucujici> listPredmetVyucujici;
    @FXML
    private TextField textPlanRok;
    @FXML
    private RadioButton radPredmetRoleGarant;
    @FXML
    private ToggleGroup vyuc_role;
    @FXML
    private RadioButton radPredmetRolePrednasejici;
    @FXML
    private RadioButton radPredmetRoleCvicici;
    @FXML
    private Spinner<Integer> spinnerPredmetyHodiny;
    @FXML
    private TableColumn<Katedra, String> colKatedraFakulta;
    @FXML
    private TableView<Katedra> tableKatedra;
    @FXML
    private TableColumn<Obor, String> colOborForma;
    @FXML
    private ComboBox<String> comboOborForma;
    @FXML
    private TableView<Plan> tablePlany;
    @FXML
    private TableColumn<Plan, Integer> colPlanRok;
    @FXML
    private TableColumn<Predmet, String> colPredmetSemestr;
    @FXML
    private TableColumn<Predmet, String> colPredmetKategorie;
    @FXML
    private TableColumn<Predmet, Integer> colPredmetRocnik;
    @FXML
    private TableColumn<Predmet, String> colPredmetPlan;
    @FXML
    private TableColumn<?, ?> colPredmetZkratka;
    @FXML
    private ComboBox<String> comboPredmetKategorie;
    @FXML
    private Spinner<Integer> spinnerPredmetRocnik;
    @FXML
    private ComboBox<Plan> comboPredmetPlan;
    @FXML
    private TextField textPredmetZkratka;
    @FXML
    private RadioButton radPredmetSemestrLetni;
    @FXML
    private ToggleGroup semestr;
    @FXML
    private RadioButton radPredmetSemestrZimni;

    private FakultaManager fakultaM;
    private KatedraManager katedraM;
    private OborManager oborM;
    private PlanManager planM;
    private MistnostManager mistnostM;
    private VyucujiciManager vyucujiciM;
    private PredmetManager predmetM;
    private RozvrhovaAktivitaManager rozvrhM;
    private UserManager userM;

    private ArrayList<Vyucujici> vyucujiciList;
    private ArrayList<Predmet> predmetyList;
    private ArrayList<RozvrhovaAktivita> rozvrhList;
    private ArrayList<Fakulta> fakultyList;
    private ArrayList<Katedra> katedryList;
    private ArrayList<Mistnost> mistnostList;
    private ArrayList<Obor> oborList;
    private ArrayList<Plan> planList;
    private ArrayList<User> userList;

    @FXML
    private Button buttonLogout;
    @FXML
    private RadioButton radPredmetRoleOboje;
    @FXML
    private Tab tabRozvrh;
    @FXML
    private Spinner<Integer> spinnerRozvrhKapacita;
    @FXML
    private ComboBox<String> comboRozvrhZpusob;
    @FXML
    private Button buttonPredmetUpravit;
    @FXML
    private TableColumn<?, ?> colRozvrhZpusob;
    @FXML
    private ListView<User> listUser;
    @FXML
    private TextField textUserName;
    @FXML
    private PasswordField textUserPassword;
    @FXML
    private ComboBox<Vyucujici> comboUserVyucujici;
    @FXML
    private ComboBox<String> comboUserRole;
    @FXML
    private Button buttonUserChooseFile;
    @FXML
    private Button buttonUserCreate;
    @FXML
    private Button buttonUserEdit;
    @FXML
    private Button buttonUserDelete;
    @FXML
    private ImageView imageViewUser;
    @FXML
    private Label labelUserImageURL;

    InputStream fileStream;
    @FXML
    private Button buttonMistnostFile;
    @FXML
    private Tab tabUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "FXMLLogin.fxml"
            ));

            fxmlLoader.load();

            FXMLLoginController loginController = fxmlLoader.getController();
            if (loginController.isBtnClicked()) {
                this.loggedUser = loginController.getLoggedUser();
                this.conn = loginController.getConn();
                System.out.println(loggedUser + ", " + conn);
            }
            //Image
            imageViewUser.setImage(new Image(getClass().getResource("noImage.png").toString()));

            //Entity mangers
            fakultaM = new FakultaManager(conn);
            katedraM = new KatedraManager(conn);
            oborM = new OborManager(conn);
            planM = new PlanManager(conn);
            mistnostM = new MistnostManager(conn);
            vyucujiciM = new VyucujiciManager(conn);
            predmetM = new PredmetManager(conn);
            rozvrhM = new RozvrhovaAktivitaManager(conn);
            userM = new UserManager(conn);

            //Vybírání všech dat z databáze
            vyucujiciList = vyucujiciM.getVyucujiciAll();
            predmetyList = predmetM.getPredmetAll();

            fakultyList = fakultaM.getFakultyAll();
            katedryList = katedraM.getKatedraAll();
            mistnostList = mistnostM.getMistnostAll();
            oborList = oborM.getOborAll();
            planList = planM.getPlanAll();
            userList = userM.getUserAll();

            //Plnění comboboxů
            //Vyucujici
            comboVyucujiciKatedra.getItems().addAll(katedryList);
            comboVyucujiciKatedra.getSelectionModel().selectFirst();

            //Rozvrh
            if (loggedUser.getRole().equals("Admin")) {
                comboSelectRozvrh.getItems().addAll(vyucujiciList);
                comboSelectRozvrh.getSelectionModel().selectFirst();
            } else {
                for (Vyucujici vyucujici : vyucujiciList) {
                    if (loggedUser.getVyucujici() != null) {
                        if (vyucujici.getId() == loggedUser.getVyucujici().getId()) {
                            comboSelectRozvrh.getItems().add(vyucujici);
                            comboSelectRozvrh.getSelectionModel().selectFirst();
                        }
                    }
                }
            }
            comboRozvrhRok.getItems().addAll(
                    new Rok(2018, "2018/2019"),
                    new Rok(2017, "2017/2018"),
                    new Rok(2016, "2016/2017"),
                    new Rok(2015, "2015/2016"),
                    new Rok(2014, "2014/2015")
            );
            comboRozvrhRok.getSelectionModel().selectFirst();

            comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
            comboRozvrhPredmet.getSelectionModel().selectFirst();
            comboRozvrhPredmet.getSelectionModel().selectFirst();

            comboRozvrhDen.getItems().addAll("Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek");
            comboRozvrhDen.getSelectionModel().selectFirst();

            comboRozvrhMistnost.getItems().addAll(mistnostList);
            comboRozvrhMistnost.getSelectionModel().selectFirst();

            comboRozvrhZpusob.getItems().addAll("Přednáška", "Cvičení", "Seminář");
            comboRozvrhZpusob.getSelectionModel().selectFirst();

            //Predmet
            comboPredmetKategorie.getItems().addAll("A", "B", "C");
            comboPredmetKategorie.getSelectionModel().selectFirst();

            comboPredmetPlan.getItems().addAll(planList);
            comboPredmetPlan.getSelectionModel().selectFirst();

            comboPredmetVyucujici.getItems().addAll(vyucujiciList);
            comboPredmetVyucujici.getSelectionModel().selectFirst();

            comboPredmetZakonceni.getItems().addAll("Zkouška", "Zápočet");
            comboPredmetZakonceni.getSelectionModel().selectFirst();

            //Katedry
            comboKatedraFakulta.getItems().addAll(fakultyList);
            comboKatedraFakulta.getSelectionModel().selectFirst();

            //Obory
            comboOborForma.getItems().addAll("Prezenční", "Dálková", "Mixovaná");
            comboOborForma.getSelectionModel().selectFirst();

            //Plány
            comboPlanObor.getItems().addAll(oborList);
            comboPlanObor.getSelectionModel().selectFirst();

            //User
            comboUserVyucujici.getItems().addAll(getVyucujiciWithoutUser());
            comboUserVyucujici.getItems().add(0, new Vyucujici(0, "--Žádný", "vyučující--", null, null, null));
            comboUserVyucujici.getSelectionModel().selectFirst();
            comboUserRole.getItems().addAll("Admin", "Uzivatel", "Normal");
            comboUserRole.getSelectionModel().selectFirst();

            //Plnění tabulek
            //Fakulty
            colFakultaId.setCellValueFactory(new PropertyValueFactory("id"));
            colFakultaNazev.setCellValueFactory(new PropertyValueFactory("nazev"));
            colFakultaZkratka.setCellValueFactory(new PropertyValueFactory("zkratka"));
            tableFakulta.getItems().addAll(fakultyList);

            //Katedry
            colKatedraId.setCellValueFactory(new PropertyValueFactory("id"));
            colKatedraNazev.setCellValueFactory(new PropertyValueFactory("nazev"));
            colKatedraZkratka.setCellValueFactory(new PropertyValueFactory("zkratka"));
            colKatedraFakulta.setCellValueFactory(new PropertyValueFactory("fakulta"));
            tableKatedra.getItems().addAll(katedryList);

            //Obory
            colOborId.setCellValueFactory(new PropertyValueFactory("id"));
            colOborNazev.setCellValueFactory(new PropertyValueFactory("nazev"));
            colOborOdhad.setCellValueFactory(new PropertyValueFactory("odhad"));
            colOborForma.setCellValueFactory(new PropertyValueFactory("forma"));
            tableObory.getItems().addAll(oborList);

            //Plany
            colPlanId.setCellValueFactory(new PropertyValueFactory("id"));
            colPlanJmeno.setCellValueFactory(new PropertyValueFactory("nazev"));
            colPlanObor.setCellValueFactory(new PropertyValueFactory("obor"));
            colPlanRok.setCellValueFactory(new PropertyValueFactory("rok"));
            tablePlany.getItems().addAll(planList);

            //Mistnosti
            colMistnostId.setCellValueFactory(new PropertyValueFactory("id"));
            colMistnostOznaceni.setCellValueFactory(new PropertyValueFactory("oznaceni"));
            colMisnostKapacita.setCellValueFactory(new PropertyValueFactory("kapacita"));
            tableMistnost.getItems().addAll(mistnostList);

            //Vyucujici
            colVyucId.setCellValueFactory(new PropertyValueFactory("id"));
            colVyucJmeno.setCellValueFactory(new PropertyValueFactory("jmeno"));
            colVyucPrijmeni.setCellValueFactory(new PropertyValueFactory("prijmeni"));
            colVyucEmail.setCellValueFactory(new PropertyValueFactory("email"));
            colVyucTelefon.setCellValueFactory(new PropertyValueFactory("telefon"));
            colVyucKatedra.setCellValueFactory(new PropertyValueFactory("katedra"));
            tableVyucujici.getItems().addAll(vyucujiciList);

            //Predmety
            colPredmetId.setCellValueFactory(new PropertyValueFactory("id"));
            colPredmetNazev.setCellValueFactory(new PropertyValueFactory("nazev"));
            colPredmetSemestr.setCellValueFactory(new PropertyValueFactory("semestr"));
            colPredmetRocnik.setCellValueFactory(new PropertyValueFactory("rocnik"));
            colPredmetKategorie.setCellValueFactory(new PropertyValueFactory("kategorie"));
            colPredmetPlan.setCellValueFactory(new PropertyValueFactory("plan"));
            colPrdemtZpusob.setCellValueFactory(new PropertyValueFactory("zakonceni"));
            colPredmetZkratka.setCellValueFactory(new PropertyValueFactory("zkratka"));
            tablePredmet.getItems().addAll(predmetyList);

            //Rozvrh
            colRozvrhId.setCellValueFactory(new PropertyValueFactory("id"));
            colRozvrhTyden.setCellValueFactory(new PropertyValueFactory("tyden"));
            colRozvrhOd.setCellValueFactory(new PropertyValueFactory("zacatek"));
            colRozvrhDo.setCellValueFactory(new PropertyValueFactory("konec"));
            colRozvrhSchvaleno.setCellValueFactory(new PropertyValueFactory("schvaleno"));
            colRozvrhPredmet.setCellValueFactory(new PropertyValueFactory("predmet"));
            colRozvrhDen.setCellValueFactory(new PropertyValueFactory("den"));
            colRozvrhMistnost.setCellValueFactory(new PropertyValueFactory("mistnost"));
            colRozvrhVyucujici.setCellValueFactory(new PropertyValueFactory("vyucujici"));
            colRozvrhZpusob.setCellValueFactory(new PropertyValueFactory("zpusobVyuky"));
            if (loggedUser.getVyucujici() != null) {
                rozvrhList = rozvrhM.getRozvrhovaAktivitaAllByVyucujiciRok(
                        comboSelectRozvrh.getSelectionModel().getSelectedItem().getId(),
                        comboRozvrhRok.getSelectionModel().getSelectedItem().getRok()
                );
                tableRozvrh.getItems().addAll(rozvrhList);
            }

            SpinnerValueFactory<Integer> rocnikValFac
                    = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1);
            SpinnerValueFactory<Integer> hodinyValFac
                    = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
            SpinnerValueFactory<Integer> kapacitaValFac
                    = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, 1);

            spinnerPredmetRocnik.setValueFactory(rocnikValFac);
            spinnerPredmetyHodiny.setValueFactory(hodinyValFac);
            spinnerRozvrhKapacita.setValueFactory(kapacitaValFac);

            listUser.getItems().addAll(userList);

            if (!loggedUser.getRole().equals("Admin")) {
                buttonFakultaSmazat.setDisable(true);
                buttonFakultaUpravit.setDisable(true);
                buttonFakultaVytvorit.setDisable(true);
                buttonKatedraSmazat.setDisable(true);
                buttonKatedraUpravit.setDisable(true);
                buttonKatedraVytvorit.setDisable(true);
                buttonMistnostSmazat.setDisable(true);
                buttonMistnostUpravit.setDisable(true);
                buttonMistnostVytvorit.setDisable(true);
                buttonOborSmazat.setDisable(true);
                buttonOborUpravit.setDisable(true);
                buttonOborVytvorit.setDisable(true);
                buttonPlanAddVyucujici.setDisable(true);
                buttonPlanPridat.setDisable(true);
                buttonPlanSmazat.setDisable(true);
                buttonPlanUpravit.setDisable(true);
                buttonPredmetSmazat.setDisable(true);
                buttonPredmetVytvorit.setDisable(true);
                if (!loggedUser.getRole().equals("Uzivatel")) {
                    buttonRozvrhSmazat.setDisable(true);
                    buttonRozvrhUpravit.setDisable(true);
                    buttonRozvrhVytvorit.setDisable(true);
                }
                buttonVyucujiciSmazat.setDisable(true);
                buttonVyucujiciUpravit.setDisable(true);
                buttonVyucujiciVytvorit.setDisable(true);
                checkRozvrhSchvaleno.setDisable(true);
                buttonMistnostFile.setDisable(true);
                tabUsers.setDisable(true);
                buttonPredmet.setDisable(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    
//    public void initData(User user){
//        loggedUser = user;       
//    }

    @FXML
    private void onSelectRozvrhAction(ActionEvent event) throws SQLException {
        if (!tabRozvrh.isSelected() || loggedUser.getVyucujici() == null) {
            return;
        }
        tableRozvrh.getItems().clear();
        rozvrhList = rozvrhM.getRozvrhovaAktivitaAllByVyucujiciRok(
                comboSelectRozvrh.getSelectionModel().getSelectedItem().getId(),
                comboRozvrhRok.getSelectionModel().getSelectedItem().getRok()
        );
        tableRozvrh.getItems().addAll(rozvrhList);
        comboRozvrhPredmet.getItems().clear();
        comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
        comboRozvrhPredmet.getSelectionModel().selectFirst();
    }

    @FXML
    private void logoutAction(ActionEvent event) throws IOException, SQLException {
        Stage stageOld = (Stage) buttonLogout.getScene().getWindow();
        loggedUser = null;
        conn.close();
        FXMLLoginController.setBtnClicked(false);
        FXMLLoginController.setConn(null);
        FXMLLoginController.setLoggedUser(null);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");

        stageOld.hide();
        stage.showAndWait();
    }

    //FAKULTY ACTIONS-----------------------------------------------------------
    @FXML
    private void getFakultaFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tableFakulta.getSelectionModel().getSelectedItem() != null) {
            Fakulta fakulta = tableFakulta.getSelectionModel().getSelectedItem();

            textFakultaNazev.setText(fakulta.getNazev());
            textFakultaZkratka.setText(fakulta.getZkratka());

            buttonFakultaUpravit.setDisable(false);
            buttonFakultaSmazat.setDisable(false);
        }
    }

    @FXML
    private void createFakultaAction(ActionEvent event) throws SQLException {
        try {
            Fakulta fakulta = new Fakulta(
                    0,
                    textFakultaNazev.getText(),
                    textFakultaZkratka.getText()
            );
            buttonFakultaVytvorit.setDisable(true);

            fakultaM.addFakulta(fakulta);

            fakultyList = fakultaM.getFakultyAll();
            refreshTable(tableFakulta, fakultyList);
            comboKatedraFakulta.getItems().clear();
            comboKatedraFakulta.getItems().addAll(fakultyList);
            comboKatedraFakulta.getSelectionModel().selectFirst();

            buttonFakultaVytvorit.setDisable(false);
        } catch (SQLException ex) {
            buttonFakultaVytvorit.setDisable(false);
            AlertWindow.showError(
                    "SQL error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteFakultaAction(ActionEvent event) throws SQLException {
        if (tableFakulta.getSelectionModel().getSelectedItem() != null) {
            try {

                Fakulta fakulta = tableFakulta.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + fakulta.getNazev() + "?"
                ).get() == ButtonType.OK) {
                    fakultaM.removeFakultaById(fakulta);

                    fakultyList = fakultaM.getFakultyAll();
                    refreshTable(tableFakulta, fakultyList);
                    comboKatedraFakulta.getItems().clear();
                    comboKatedraFakulta.getItems().addAll(fakultyList);
                    comboKatedraFakulta.getSelectionModel().selectFirst();

                    buttonFakultaSmazat.setDisable(true);
                }

            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu do databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editFakultaAction(ActionEvent event) throws SQLException {
        if (tableFakulta.getSelectionModel().getSelectedItem() != null) {
            try {
                Fakulta fakulta = tableFakulta.getSelectionModel().getSelectedItem();
                fakulta.setNazev(textFakultaNazev.getText());
                fakulta.setZkratka(textFakultaZkratka.getText());

                buttonFakultaSmazat.setDisable(true);

                fakultaM.editFakultaById(fakulta);

                buttonFakultaSmazat.setDisable(false);
                fakultyList = fakultaM.getFakultyAll();
                refreshTable(tableFakulta, fakultyList);
                comboKatedraFakulta.getItems().clear();
                comboKatedraFakulta.getItems().addAll(fakultyList);
                comboKatedraFakulta.getSelectionModel().selectFirst();

                buttonFakultaUpravit.setDisable(true);
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //MISTNOSTI ACTIONS---------------------------------------------------------
    @FXML
    private void getMistnostFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tableMistnost.getSelectionModel().getSelectedItem() != null) {
            Mistnost mistnost = tableMistnost.getSelectionModel().getSelectedItem();

            textMistnostOznacecni.setText(mistnost.getOznaceni());
            textMistnostKapacita.setText(String.valueOf(mistnost.getKapacita()));

            buttonMistnostUpravit.setDisable(false);
            buttonMistnostSmazat.setDisable(false);
        }
    }

    @FXML
    private void createMistnostAction(ActionEvent event) {
        try {
            Mistnost mistnost = new Mistnost(
                    0,
                    Integer.valueOf(textMistnostKapacita.getText()),
                    textMistnostOznacecni.getText()
            );
            buttonMistnostVytvorit.setDisable(true);

            mistnostM.addMistnost(mistnost);

            mistnostList = mistnostM.getMistnostAll();
            refreshTable(tableMistnost, mistnostList);
            comboRozvrhMistnost.getItems().clear();
            comboRozvrhMistnost.getItems().addAll(mistnostList);
            comboRozvrhMistnost.getSelectionModel().selectFirst();

            buttonMistnostVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException ex) {
            buttonMistnostVytvorit.setDisable(false);
            AlertWindow.showError(
                    "SQL error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteMistnostAction(ActionEvent event) {
        if (tableMistnost.getSelectionModel().getSelectedItem() != null) {
            try {

                Mistnost mistnost = tableMistnost.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + mistnost.getOznaceni() + "?"
                ).get() == ButtonType.OK) {
                    mistnostM.removeMistnostById(mistnost);

                    mistnostList = mistnostM.getMistnostAll();
                    refreshTable(tableMistnost, mistnostList);
                    comboRozvrhMistnost.getItems().clear();
                    comboRozvrhMistnost.getItems().addAll(mistnostList);
                    comboRozvrhMistnost.getSelectionModel().selectFirst();

                    buttonMistnostSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu do databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editMistnostAction(ActionEvent event) {
        if (tableMistnost.getSelectionModel().getSelectedItem() != null) {
            try {
                Mistnost mistnost = tableMistnost.getSelectionModel().getSelectedItem();
                mistnost.setOznaceni(textMistnostOznacecni.getText());
                mistnost.setKapacita(Integer.valueOf(textMistnostKapacita.getText()));

                buttonMistnostUpravit.setDisable(true);

                mistnostM.editMistnostById(mistnost);

                buttonMistnostUpravit.setDisable(false);
                mistnostList = mistnostM.getMistnostAll();
                refreshTable(tableMistnost, mistnostList);
                comboRozvrhMistnost.getItems().clear();
                comboRozvrhMistnost.getItems().addAll(mistnostList);
                comboRozvrhMistnost.getSelectionModel().selectFirst();

                buttonMistnostUpravit.setDisable(true);
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void addMistnostFromFIle(ActionEvent event) {
        Stage stage = (Stage) buttonUserChooseFile.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open csv file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        Image image;
        File file = null;
        try {
            file = fileChooser.showOpenDialog(stage);
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Byl vybrán špatný formát");
            alert.setContentText("Chyba: " + ex);
            alert.showAndWait();
        }
        if (file != null) {
            try {
                if (file.getName().endsWith(".xml")) {
                    mistnostM.addMistnostFromFile(file);
                    mistnostList = mistnostM.getMistnostAll();
                    refreshTable(tableMistnost, mistnostList);
                }
            } catch (SQLException | FileNotFoundException ex) {
                AlertWindow.showError(
                        "Error",
                        "Nahrání souboru se nezdařilo!",
                        ex.toString()
                );
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                AlertWindow.showError(
                        "Error",
                        "Nahrání souboru se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    //OBORY ACTIONS-------------------------------------------------------------
    @FXML
    private void getOborFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tableObory.getSelectionModel().getSelectedItem() != null) {
            Obor obor = tableObory.getSelectionModel().getSelectedItem();

            textOborNazev.setText(obor.getNazev());
            textOborOdhad.setText(Integer.toString(obor.getOdhad()));
            comboOborForma.getSelectionModel().select(obor.getForma());

            buttonOborUpravit.setDisable(false);
            buttonOborSmazat.setDisable(false);
        }
    }

    @FXML
    private void createOborAction(ActionEvent event) {
        try {
            Obor obor = new Obor(
                    0,
                    textOborNazev.getText(),
                    Integer.valueOf(textOborOdhad.getText()),
                    comboOborForma.getSelectionModel().getSelectedItem()
            );
            buttonOborVytvorit.setDisable(true);

            oborM.addObor(obor);

            oborList = oborM.getOborAll();
            refreshTable(tableObory, oborList);

            buttonOborVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException ex) {
            buttonOborVytvorit.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteOborAction(ActionEvent event) {
        if (tableObory.getSelectionModel().getSelectedItem() != null) {
            try {

                Obor obor = tableObory.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + obor.getNazev() + "?"
                ).get() == ButtonType.OK) {
                    oborM.removeOborById(obor);

                    oborList = oborM.getOborAll();
                    refreshTable(tableObory, oborList);
                    buttonOborSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "Error",
                        "Odebrání záznamu do databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editOborAction(ActionEvent event) {
        if (tableObory.getSelectionModel().getSelectedItem() != null) {
            try {
                Obor obor = tableObory.getSelectionModel().getSelectedItem();
                obor.setNazev(textOborNazev.getText());
                obor.setOdhad(Integer.valueOf(textOborOdhad.getText()));

                buttonMistnostUpravit.setDisable(true);

                oborM.editOborById(obor);

                buttonOborUpravit.setDisable(false);
                oborList = oborM.getOborAll();
                refreshTable(tableObory, oborList);
                buttonMistnostUpravit.setDisable(true);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "Error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //PLÁNY ACTIONS-------------------------------------------------------------
    @FXML
    private void getPlanFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tablePlany.getSelectionModel().getSelectedItem() != null) {
            Plan plan = tablePlany.getSelectionModel().getSelectedItem();

            textPlanJmeno.setText(plan.getNazev());
            textPlanRok.setText(Integer.toString(plan.getRok().getRok()));
            comboPlanObor.getSelectionModel().select(plan.getObor());

            buttonPlanUpravit.setDisable(false);
            buttonPlanSmazat.setDisable(false);
        }
    }

    @FXML
    private void createPlanAction(ActionEvent event) {
        try {
            Plan plan = new Plan(
                    0,
                    textPlanJmeno.getText(),
                    comboPlanObor.getSelectionModel().getSelectedItem(),
                    new Rok(Integer.valueOf(textPlanRok.getText()))
            );
            buttonPlanPridat.setDisable(true);

            checkPlanForDuplicity(plan);
            planM.addPlan(plan);

            planList = planM.getPlanAll();
            refreshTable(tablePlany, planList);
            comboPredmetPlan.getItems().clear();
            comboPredmetPlan.getItems().addAll(planList);
            comboPredmetPlan.getSelectionModel().selectFirst();
            buttonPlanPridat.setDisable(false);
        } catch (SQLException | NumberFormatException | IOException ex) {
            buttonPlanPridat.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deletePlanAction(ActionEvent event) {
        if (tablePlany.getSelectionModel().getSelectedItem() != null) {
            try {
                Plan plan = tablePlany.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + plan.getNazev() + "?"
                ).get() == ButtonType.OK) {
                    planM.removePlanById(plan);

                    planList = planM.getPlanAll();
                    refreshTable(tablePlany, planList);
                    comboPredmetPlan.getItems().clear();
                    comboPredmetPlan.getItems().addAll(planList);
                    comboPredmetPlan.getSelectionModel().selectFirst();

                    buttonPlanSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu do databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editPlanAction(ActionEvent event) {
        if (tablePlany.getSelectionModel().getSelectedItem() != null) {
            try {
                Plan plan = tablePlany.getSelectionModel().getSelectedItem();
                plan.setNazev(textPlanJmeno.getText());
                plan.setRok(new Rok(Integer.valueOf(textPlanRok.getText())));
                plan.setObor(comboPlanObor.getSelectionModel().getSelectedItem());

                buttonPlanUpravit.setDisable(true);

                String message = planM.editPlanById(plan);
                if (!message.equals("SUCCESS")) {
                    buttonPredmetVytvorit.setDisable(false);
                    throw new SQLException(message);
                }

                buttonPlanUpravit.setDisable(false);
                planList = planM.getPlanAll();
                refreshTable(tablePlany, planList);
                comboPredmetPlan.getItems().clear();
                comboPredmetPlan.getItems().addAll(planList);
                comboPredmetPlan.getSelectionModel().selectFirst();

                buttonPlanUpravit.setDisable(true);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //KATEDERA ACTIONS----------------------------------------------------------
    @FXML
    private void getKatedraFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tableKatedra.getSelectionModel().getSelectedItem() != null) {
            Katedra katedra = tableKatedra.getSelectionModel().getSelectedItem();

            textKatedraNazev.setText(katedra.getNazev());
            textKatedraZkratka.setText(katedra.getZkratka());
            comboKatedraFakulta.getSelectionModel().select(katedra.getFakulta());

            buttonKatedraUpravit.setDisable(false);
            buttonKatedraSmazat.setDisable(false);
        }
    }

    @FXML
    private void createKatedraAction(ActionEvent event) {
        try {
            Katedra katedra = new Katedra(
                    0,
                    textKatedraNazev.getText(),
                    textKatedraZkratka.getText(),
                    comboKatedraFakulta.getSelectionModel().getSelectedItem()
            );
            buttonKatedraVytvorit.setDisable(true);

            katedraM.addKatedra(katedra);

            katedryList = katedraM.getKatedraAll();
            refreshTable(tableKatedra, katedryList);
            comboVyucujiciKatedra.getItems().clear();
            comboVyucujiciKatedra.getItems().addAll(katedryList);
            comboVyucujiciKatedra.getSelectionModel().selectFirst();

            buttonKatedraVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException ex) {
            buttonKatedraVytvorit.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteKatedraAction(ActionEvent event) {
        if (tableKatedra.getSelectionModel().getSelectedItem() != null) {
            try {
                Katedra katedra = tableKatedra.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + katedra.getNazev() + "?"
                ).get() == ButtonType.OK) {
                    katedraM.removeKatedraById(katedra);

                    katedryList = katedraM.getKatedraAll();
                    refreshTable(tableKatedra, katedryList);
                    comboVyucujiciKatedra.getItems().clear();
                    comboVyucujiciKatedra.getItems().addAll(katedryList);
                    comboVyucujiciKatedra.getSelectionModel().selectFirst();
                    buttonKatedraSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu do databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editKatedraAction(ActionEvent event) {
        if (tableKatedra.getSelectionModel().getSelectedItem() != null) {
            try {
                Katedra katedra = tableKatedra.getSelectionModel().getSelectedItem();
                katedra.setNazev(textKatedraNazev.getText());
                katedra.setZkratka(textKatedraZkratka.getText());
                katedra.setFakulta(comboKatedraFakulta.getSelectionModel().getSelectedItem());

                buttonKatedraUpravit.setDisable(true);

                katedraM.editKatedraById(katedra);

                katedryList = katedraM.getKatedraAll();
                refreshTable(tableKatedra, katedryList);
                comboVyucujiciKatedra.getItems().clear();
                comboVyucujiciKatedra.getItems().addAll(katedryList);
                comboVyucujiciKatedra.getSelectionModel().selectFirst();
                buttonKatedraUpravit.setDisable(false);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //VYUCUJICI ACTIONS---------------------------------------------------------
    @FXML
    private void getVyucujiciFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (tableVyucujici.getSelectionModel().getSelectedItem() != null) {
            Vyucujici vyucujici = tableVyucujici.getSelectionModel().getSelectedItem();

            textVyucujiciJmeno.setText(vyucujici.getJmeno());
            textVyucujiciPrijmeni.setText(vyucujici.getPrijmeni());
            textVyucujiciEmail.setText(vyucujici.getEmail());
            textVyucujiciTelefon.setText(vyucujici.getTelefon());
            comboVyucujiciKatedra.getSelectionModel().select(vyucujici.getKatedra());

            buttonVyucujiciUpravit.setDisable(false);
            buttonVyucujiciSmazat.setDisable(false);
        }
    }

    @FXML
    private void createVyucujiciAction(ActionEvent event) {
        try {
            Vyucujici vyucujici = new Vyucujici(
                    0,
                    textVyucujiciJmeno.getText(),
                    textVyucujiciPrijmeni.getText(),
                    textVyucujiciEmail.getText(),
                    textVyucujiciTelefon.getText(),
                    comboVyucujiciKatedra.getSelectionModel().getSelectedItem()
            );
            buttonVyucujiciVytvorit.setDisable(true);

            vyucujiciM.addVyucujici(vyucujici);

            vyucujiciList = vyucujiciM.getVyucujiciAll();
            refreshTable(tableVyucujici, vyucujiciList);
            comboPredmetVyucujici.getItems().clear();
            comboPredmetVyucujici.getItems().addAll(vyucujiciList);
            comboPredmetVyucujici.getSelectionModel().selectFirst();

            comboSelectRozvrh.getItems().clear();
            comboSelectRozvrh.getItems().addAll(vyucujiciList);
            comboSelectRozvrh.getSelectionModel().selectFirst();

            comboUserVyucujici.getItems().addAll(getVyucujiciWithoutUser());
            comboUserVyucujici.getItems().add(0, new Vyucujici(0, "--Žádný", "vyučující--", null, null, null));
            comboUserVyucujici.getSelectionModel().selectFirst();

            buttonVyucujiciVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException ex) {
            buttonVyucujiciVytvorit.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteVyucujiciAction(ActionEvent event) {
        if (tableVyucujici.getSelectionModel().getSelectedItem() != null) {
            try {
                Vyucujici vyucujici = tableVyucujici.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + vyucujici.getJmeno() + " " + vyucujici.getPrijmeni() + "?"
                ).get() == ButtonType.OK) {
                    vyucujiciM.removeVyucujiciById(vyucujici);

                    vyucujiciList = vyucujiciM.getVyucujiciAll();
                    refreshTable(tableVyucujici, vyucujiciList);
                    comboPredmetVyucujici.getItems().clear();
                    comboPredmetVyucujici.getItems().addAll(vyucujiciList);
                    comboPredmetVyucujici.getSelectionModel().selectFirst();

                    comboSelectRozvrh.getItems().clear();
                    comboSelectRozvrh.getItems().addAll(vyucujiciList);
                    comboSelectRozvrh.getSelectionModel().selectFirst();

                    comboUserVyucujici.getItems().addAll(getVyucujiciWithoutUser());
                    comboUserVyucujici.getItems().add(0, new Vyucujici(0, "--Žádný", "vyučující--", null, null, null));
                    comboUserVyucujici.getSelectionModel().selectFirst();

                    buttonVyucujiciSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu z databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editVyucujiciAction(ActionEvent event) {
        if (tableVyucujici.getSelectionModel().getSelectedItem() != null) {
            try {
                Vyucujici vyucujici = tableVyucujici.getSelectionModel().getSelectedItem();
                vyucujici.setJmeno(textVyucujiciJmeno.getText());
                vyucujici.setPrijmeni(textVyucujiciPrijmeni.getText());
                vyucujici.setEmail(textVyucujiciEmail.getText());
                vyucujici.setKatedra(comboVyucujiciKatedra.getSelectionModel().getSelectedItem());

                buttonVyucujiciUpravit.setDisable(true);

                vyucujiciM.editVyucujiciById(vyucujici);

                vyucujiciList = vyucujiciM.getVyucujiciAll();
                refreshTable(tableVyucujici, vyucujiciList);
                comboPredmetVyucujici.getItems().clear();
                comboPredmetVyucujici.getItems().addAll(vyucujiciList);
                comboPredmetVyucujici.getSelectionModel().selectFirst();

                comboSelectRozvrh.getItems().clear();
                comboSelectRozvrh.getItems().addAll(vyucujiciList);
                comboSelectRozvrh.getSelectionModel().selectFirst();

                comboUserVyucujici.getItems().addAll(getVyucujiciWithoutUser());
                comboUserVyucujici.getItems().add(0, new Vyucujici(0, "--Žádný", "vyučující--", null, null, null));
                comboUserVyucujici.getSelectionModel().selectFirst();

                buttonVyucujiciUpravit.setDisable(false);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //PŘEDMĚTY ACTIONS----------------------------------------------------------
    @FXML
    private void getPredmetFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        try {
            if (tablePredmet.getSelectionModel().getSelectedItem() != null) {
                Predmet predmet = tablePredmet.getSelectionModel().getSelectedItem();

                textPredmetNazev.setText(predmet.getNazev());
                textPredmetZkratka.setText(predmet.getZkratka());
                comboPredmetZakonceni.getSelectionModel().select(predmet.getZakonceni());
                if (predmet.getSemestr().equals("Letni")) {
                    semestr.selectToggle(radPredmetSemestrLetni);
                } else {
                    semestr.selectToggle(radPredmetSemestrLetni);
                }
                comboPredmetKategorie.getSelectionModel().select(predmet.getKategorie());
                comboPredmetPlan.getSelectionModel().select(predmet.getPlan());
                spinnerPredmetRocnik.getValueFactory().setValue(predmet.getRocnik());
                listPredmetVyucujici.getItems().clear();
                listPredmetVyucujici.getItems().addAll(vyucujiciM.getVyucujiciByPredmetAll(predmet.getId()));

                buttonPredmetUpravit.setDisable(false);
                buttonPredmetSmazat.setDisable(false);
            }
        } catch (SQLException ex) {
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void createPredmetAction(ActionEvent event) {
        try {
            String semestrChoice = null;
            if (radPredmetSemestrLetni.isSelected()) {
                semestrChoice = "Letni";
            } else {
                semestrChoice = "Zimni";
            }
            Predmet predmet = new Predmet(
                    0,
                    textPredmetNazev.getText(),
                    textPredmetZkratka.getText(),
                    semestrChoice,
                    spinnerPredmetRocnik.getValue(),
                    comboPredmetKategorie.getSelectionModel().getSelectedItem(),
                    comboPredmetZakonceni.getSelectionModel().getSelectedItem(),
                    comboPredmetPlan.getSelectionModel().getSelectedItem(),
                    null
            );
            buttonPredmetVytvorit.setDisable(true);

            String message = predmetM.addPredmet(predmet);
            if (!message.equals("SUCCESS")) {
                buttonPredmetVytvorit.setDisable(false);
                throw new SQLException(message);
            }

            predmetyList = predmetM.getPredmetAll();
            refreshTable(tablePredmet, predmetyList);
            comboRozvrhPredmet.getItems().clear();
            comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
            comboRozvrhPredmet.getSelectionModel().selectFirst();

            buttonPredmetVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException ex) {
            buttonPredmetVytvorit.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deletePredmetAction(ActionEvent event) {
        if (tablePredmet.getSelectionModel().getSelectedItem() != null) {
            try {
                Predmet predmet = tablePredmet.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + predmet.getNazev() + "?"
                ).get() == ButtonType.OK) {
                    predmetM.removePredmetById(predmet);

                    predmetyList = predmetM.getPredmetAll();
                    refreshTable(tablePredmet, predmetyList);
                    comboRozvrhPredmet.getItems().clear();
                    comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
                    comboRozvrhPredmet.getSelectionModel().selectFirst();

                    buttonPredmetSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu z databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editPredmetAction(ActionEvent event) {
        if (tablePredmet.getSelectionModel().getSelectedItem() != null) {
            try {
                String semestrChoice = null;
                if (radPredmetSemestrLetni.isSelected()) {
                    semestrChoice = "Letni";
                } else {
                    semestrChoice = "Zimni";
                }
                Predmet predmet = tablePredmet.getSelectionModel().getSelectedItem();
                predmet.setNazev(textPredmetNazev.getText());
                predmet.setZkratka(textPredmetZkratka.getText());
                predmet.setSemestr(semestrChoice);
                predmet.setKategorie(comboPredmetKategorie.getSelectionModel().getSelectedItem());
                predmet.setPlan(comboPredmetPlan.getSelectionModel().getSelectedItem());
                predmet.setZakonceni(comboPredmetZakonceni.getSelectionModel().getSelectedItem());
                predmet.setRocnik(spinnerPredmetRocnik.getValue());

                rozvrhovaAktivita.setDisable(true);

                predmetM.editPredmetById(predmet);

                predmetyList = predmetM.getPredmetAll();
                refreshTable(tablePredmet, predmetyList);
                comboRozvrhPredmet.getItems().clear();
                comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
                comboRozvrhPredmet.getSelectionModel().selectFirst();

                rozvrhovaAktivita.setDisable(false);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void addVyucujiciToPredmetAction(ActionEvent event) throws SQLException {
        try {
            if (tablePredmet.getSelectionModel().getSelectedItem() != null) {
                Predmet predmet = tablePredmet.getSelectionModel().getSelectedItem();
                Vyucujici vyucujici = comboPredmetVyucujici.getSelectionModel().getSelectedItem();
                String role = null;
                if (radPredmetRoleGarant.isSelected()) {
                    role = "Garant";
                } else if (radPredmetRoleCvicici.isSelected()) {
                    role = "Cvičící";
                } else if (radPredmetRolePrednasejici.isSelected()) {
                    role = "Přednášející";
                } else {
                    role = "Přednášející i Cvičící";
                }
                String message = predmetM.addVyucujicToPredmet(vyucujici, predmet, role, spinnerPredmetyHodiny.getValue());
                if (!message.equals("SUCCESS")) {
                    throw new SQLException(message);
                }

                predmetyList = predmetM.getPredmetAll();
                refreshTable(tablePredmet, predmetyList);
                comboRozvrhPredmet.getItems().clear();
                comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
                comboRozvrhPredmet.getSelectionModel().selectFirst();

                listPredmetVyucujici.getItems().add(vyucujici);
            }
        } catch (SQLException | NumberFormatException ex) {
            AlertWindow.showError(
                    "SQL error",
                    "Úprava záznamu do databáze se nezdařila!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void removeVyucujiciFromPredmetAction(ActionEvent event) {
        try {
            if (listPredmetVyucujici.getSelectionModel().getSelectedItem() != null
                    && tablePredmet.getSelectionModel().getSelectedItem() != null) {
                Predmet predmet = tablePredmet.getSelectionModel().getSelectedItem();
                Vyucujici vyucujici = listPredmetVyucujici.getSelectionModel().getSelectedItem();

                predmetM.removeVyucujiciFromPredmet(vyucujici, predmet);

                predmetyList = predmetM.getPredmetAll();
                refreshTable(tablePredmet, predmetyList);
                comboRozvrhPredmet.getItems().clear();
                comboRozvrhPredmet.getItems().addAll(getPredmetyByVyucujiciRok());
                comboRozvrhPredmet.getSelectionModel().selectFirst();

                listPredmetVyucujici.getItems().remove(vyucujici);
            }
        } catch (SQLException | NumberFormatException ex) {
            AlertWindow.showError(
                    "SQL error",
                    "Úprava záznamu do databáze se nezdařila!",
                    ex.toString()
            );
        }
    }

    //ROVRH ACTIONS-------------------------------------------------------------
    @FXML
    private void getRozvrhFromTableAction(MouseEvent event) {
        if (!loggedUser.getRole().equals("Admin")) {
            if (!loggedUser.getRole().equals("Uzivatel")) {
                return;
            }
        }
        if (tableRozvrh.getSelectionModel().getSelectedItem() != null) {
            RozvrhovaAktivita rozvrhovaAktivita = tableRozvrh.getSelectionModel().getSelectedItem();

            if (rozvrhovaAktivita.getTyden().equals("Lichý")) {
                radioRozvrhLichy.setSelected(true);
            } else if (rozvrhovaAktivita.getTyden().equals("Sudý")) {
                radioRozvrhSudy.setSelected(true);
            } else {
                radioRozvrhVsechny.setSelected(true);
            }
            textRozvrhOd.setText(String.valueOf(rozvrhovaAktivita.getZacatek()));
            textRozvrhDo.setText(String.valueOf(rozvrhovaAktivita.getKonec()));
            comboRozvrhZpusob.getSelectionModel().select(rozvrhovaAktivita.getZpusobVyuky());
            if (rozvrhovaAktivita.getSchvaleno() == 1) {
                checkRozvrhSchvaleno.setSelected(true);
            } else {
                checkRozvrhSchvaleno.setSelected(false);
            }
            spinnerRozvrhKapacita.getValueFactory().setValue(rozvrhovaAktivita.getKapacita());
            comboRozvrhPredmet.getSelectionModel().select(rozvrhovaAktivita.getPredmet());
            comboRozvrhDen.getSelectionModel().select(rozvrhovaAktivita.getDen());
            comboRozvrhMistnost.getSelectionModel().select(rozvrhovaAktivita.getMistnost());

            buttonRozvrhUpravit.setDisable(false);
            buttonRozvrhSmazat.setDisable(false);
        }
    }

    @FXML
    private void createRozvrhAction(ActionEvent event) {
        try {
            String tydenChoice = null;
            if (radioRozvrhLichy.isSelected()) {
                tydenChoice = "Lichý";
            } else if (radioRozvrhSudy.isSelected()) {
                tydenChoice = "Sudý";
            } else {
                tydenChoice = "Oba";
            }
            if (Integer.valueOf(textRozvrhOd.getText()) >= Integer.valueOf(textRozvrhDo.getText())) {
                throw new IOException("Začátek akce nemůže být později než její kone!");
            }
            RozvrhovaAktivita rozvrhovaAktivita = new RozvrhovaAktivita(
                    0,
                    tydenChoice,
                    Integer.valueOf(textRozvrhOd.getText()),
                    Integer.valueOf(textRozvrhDo.getText()),
                    checkRozvrhSchvaleno.isSelected() ? 1 : 0,
                    comboRozvrhDen.getSelectionModel().getSelectedItem(),
                    spinnerRozvrhKapacita.getValue(),
                    comboRozvrhPredmet.getSelectionModel().getSelectedItem(),
                    comboRozvrhZpusob.getSelectionModel().getSelectedItem(),
                    comboSelectRozvrh.getSelectionModel().getSelectedItem(),
                    comboRozvrhMistnost.getSelectionModel().getSelectedItem()
            );
            buttonRozvrhVytvorit.setDisable(true);
            boolean admin = false;
            if (loggedUser.getRole().equals("Admin")) {
                admin = true;
            }
            String message = rozvrhM.addRozvrhAktivita(rozvrhovaAktivita, admin);
            if (!message.equals("SUCCESS")) {
                buttonRozvrhVytvorit.setDisable(false);
                throw new SQLException(message);
            }

            rozvrhList = rozvrhM.getRozvrhovaAktivitaAllByVyucujiciRok(
                    comboSelectRozvrh.getSelectionModel().getSelectedItem().getId(),
                    comboRozvrhRok.getSelectionModel().getSelectedItem().getRok()
            );
            refreshTable(tableRozvrh, rozvrhList);
            buttonRozvrhVytvorit.setDisable(false);
        } catch (SQLException | NumberFormatException | IOException ex) {
            buttonRozvrhVytvorit.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteRozvrhAction(ActionEvent event) {
        if (tableRozvrh.getSelectionModel().getSelectedItem() != null) {
            try {
                RozvrhovaAktivita rozvrhovaAktivita = tableRozvrh.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + rozvrhovaAktivita.getPredmet().getNazev() + " (" + rozvrhovaAktivita.getPredmet().getPlan() + ")?"
                ).get() == ButtonType.OK) {
                    rozvrhM.removeRozvrhovaAktivitaById(rozvrhovaAktivita);

                    rozvrhList = rozvrhM.getRozvrhovaAktivitaAllByVyucujiciRok(
                            comboSelectRozvrh.getSelectionModel().getSelectedItem().getId(),
                            comboRozvrhRok.getSelectionModel().getSelectedItem().getRok()
                    );
                    refreshTable(tableRozvrh, rozvrhList);
                    buttonRozvrhSmazat.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu z databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editRozvrhAction(ActionEvent event) {
        if (tableRozvrh.getSelectionModel().getSelectedItem() != null) {
            try {
                String tydenChoice = null;
                if (radioRozvrhLichy.isSelected()) {
                    tydenChoice = "Lichý";
                } else if (radioRozvrhSudy.isSelected()) {
                    tydenChoice = "Sudý";
                } else {
                    tydenChoice = "Oba";
                }
                RozvrhovaAktivita rozvrhovaAktivita = tableRozvrh.getSelectionModel().getSelectedItem();
                rozvrhovaAktivita.setDen(comboRozvrhDen.getSelectionModel().getSelectedItem());
                rozvrhovaAktivita.setZacatek(Integer.valueOf(textRozvrhOd.getText()));
                rozvrhovaAktivita.setKonec(Integer.valueOf(textRozvrhDo.getText()));
                rozvrhovaAktivita.setMistnost(comboRozvrhMistnost.getSelectionModel().getSelectedItem());
                rozvrhovaAktivita.setPredmet(comboRozvrhPredmet.getSelectionModel().getSelectedItem());
                rozvrhovaAktivita.setZpusobVyuky(comboRozvrhZpusob.getSelectionModel().getSelectedItem());
                rozvrhovaAktivita.setSchvaleno(checkRozvrhSchvaleno.isSelected() ? 1 : 0);
                rozvrhovaAktivita.setTyden(tydenChoice);

                buttonRozvrhUpravit.setDisable(true);

                boolean admin = false;
                if (loggedUser.getRole().equals("Admin")) {
                    admin = true;
                }
                String message = rozvrhM.editRozvrhovaAktivitaById(rozvrhovaAktivita, admin);
                if (!message.equals("SUCCESS")) {
                    buttonRozvrhUpravit.setDisable(false);
                    throw new SQLException(message);
                }

                rozvrhList = rozvrhM.getRozvrhovaAktivitaAllByVyucujiciRok(
                        comboSelectRozvrh.getSelectionModel().getSelectedItem().getId(),
                        comboRozvrhRok.getSelectionModel().getSelectedItem().getRok()
                );
                refreshTable(tableRozvrh, rozvrhList);
                buttonRozvrhUpravit.setDisable(false);
            } catch (SQLException | NumberFormatException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //USER ACTIONS--------------------------------------------------------------
    @FXML
    private void getUserFromUserList(MouseEvent event) throws IOException, SQLException {
        if (!loggedUser.getRole().equals("Admin")) {
            return;
        }
        if (listUser.getSelectionModel().getSelectedItem() != null) {
            User user = listUser.getSelectionModel().getSelectedItem();

            comboUserVyucujici.getItems().clear();
            comboUserVyucujici.getItems().addAll(getVyucujiciWithoutUser());
            comboUserVyucujici.getItems().add(0, new Vyucujici(0, "--Žádný", "vyučující--", null, null, null));
            if (user.getVyucujici() != null) {
                comboUserVyucujici.getItems().add(user.getVyucujici());
            }
            comboUserVyucujici.getSelectionModel().selectFirst();

            textUserName.setText(user.getJmeno());

            if (user.getVyucujici() != null) {
                comboUserVyucujici.getSelectionModel().select(user.getVyucujici());
            } else {
                comboUserVyucujici.getSelectionModel().selectFirst();
            }
            comboUserRole.getSelectionModel().select(user.getRole());
            if (user.getObrazek() != null) {
                imageViewUser.setImage(new Image(user.getObrazek().getObrazek()));
                labelUserImageURL.setText(user.getObrazek().getPripona());
            } else {
                imageViewUser.setImage(new Image(getClass().getResource("noImage.png").toString()));
                labelUserImageURL.setText("");
            }

            buttonUserDelete.setDisable(false);
            buttonUserEdit.setDisable(false);
        }
    }

    @FXML
    private void chooseFileAction(ActionEvent event) throws MalformedURLException, FileNotFoundException {
        Stage stage = (Stage) buttonUserChooseFile.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpg/bmp files", "*.jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        Image image;
        File file = null;
        try {
            file = fileChooser.showOpenDialog(stage);
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba!");
            alert.setHeaderText("Byl vybrán špatný formát");
            alert.setContentText("Chyba: " + ex);
            alert.showAndWait();
        }
        if (file != null) {
            if (file.getName().endsWith(".jpg") || file.getName().endsWith(".bmp")) {
                image = new Image(file.toURI().toURL().toString());
                fileStream = new FileInputStream(file);
                labelUserImageURL.setText(file.getName());
                imageViewUser.setImage(image);
            }
        }
    }

    @FXML
    private void createUserAction(ActionEvent event) {
        try {
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Obrazek obrazek;
            Vyucujici vyuc;
            if (fileStream != null) {
                obrazek = new Obrazek(
                        0,
                        labelUserImageURL.getText().substring(labelUserImageURL.getText().length() - 3),
                        labelUserImageURL.getText(),
                        new Date(),
                        fileStream
                );
            } else {
                obrazek = null;
            }
            if (comboUserVyucujici.getSelectionModel().getSelectedItem().getId() == 0) {
                vyuc = null;
            } else {
                vyuc = comboUserVyucujici.getSelectionModel().getSelectedItem();
            }
            User user = new User(
                    0,
                    textUserName.getText(),
                    textUserPassword.getText(),
                    comboUserRole.getSelectionModel().getSelectedItem(),
                    obrazek,
                    vyuc
            );
            buttonUserCreate.setDisable(true);

            int result = userM.addUser(user);
            if (result == -1) {
                buttonUserCreate.setDisable(false);
                throw new IOException("Něco je špatně!");
            }

            userList = userM.getUserAll();
            listUser.getItems().clear();
            listUser.getItems().addAll(userList);

            buttonUserCreate.setDisable(false);
        } catch (SQLException | NumberFormatException | IOException ex) {
            buttonUserCreate.setDisable(false);
            AlertWindow.showError(
                    "Error",
                    "Přidání záznamu do databáze se nezdařilo!",
                    ex.toString()
            );
        }
    }

    @FXML
    private void deleteUserAction(ActionEvent event) {
        if (listUser.getSelectionModel().getSelectedItem() != null) {
            try {
                User user = listUser.getSelectionModel().getSelectedItem();

                if (AlertWindow.showDeleteConfirmation(
                        "Smazání záznamu",
                        null,
                        "Opravdu chcete smazat " + user.getJmeno() + "?"
                ).get() == ButtonType.OK) {
                    userM.removeUserById(user);

                    userList = userM.getUserAll();
                    listUser.getItems().clear();
                    listUser.getItems().addAll(userList);
                    buttonUserDelete.setDisable(true);
                }
            } catch (SQLException ex) {
                AlertWindow.showError(
                        "SQL error",
                        "Odebrání záznamu z databáze se nezdařilo!",
                        ex.toString()
                );
            }
        }
    }

    @FXML
    private void editUserAction(ActionEvent event) {
        if (listUser.getSelectionModel().getSelectedItem() != null) {
            try {

                User user = listUser.getSelectionModel().getSelectedItem();
                user.setJmeno(textUserName.getText());
                user.setHeslo(textUserPassword.getText());
                user.setRole(comboUserRole.getSelectionModel().getSelectedItem());
                user.setVyucujici(comboUserVyucujici.getSelectionModel().getSelectedItem());
                if (fileStream != null) {
                    user.setObrazek(new Obrazek(
                            0,
                            labelUserImageURL.getText().substring(labelUserImageURL.getText().length() - 3),
                            labelUserImageURL.getText(),
                            new Date(),
                            fileStream
                    ));
                } else {
                    user.setObrazek(null);
                }

                buttonUserEdit.setDisable(true);

                String message = userM.editUserById(user);
                if (!message.equals("SUCCESS")) {
                    buttonUserEdit.setDisable(false);
                    throw new SQLException(message);
                }

                userList = userM.getUserAll();
                listUser.getItems().clear();
                listUser.getItems().addAll(userList);

                buttonUserEdit.setDisable(false);
            } catch (SQLException | NumberFormatException ex) {
                buttonUserEdit.setDisable(false);
                AlertWindow.showError(
                        "SQL error",
                        "Úprava záznamu do databáze se nezdařila!",
                        ex.toString()
                );
            }
        }
    }

    //POMOCNE METODY------------------------------------------------------------
    private void refreshTable(TableView table, ArrayList list) {
        table.getItems().clear();
        table.getItems().addAll(list);
    }

    private void checkPlanForDuplicity(Plan plan) throws IOException {
        for (Plan plan1 : planList) {
            if (plan1.getNazev().equals(plan.getNazev())
                    && plan1.getRok().getRok() == plan.getRok().getRok()) {
                buttonPlanPridat.setDisable(false);
                throw new IOException("Záznamy nesmí být duplicitní!");
            }
        }
    }

    private ArrayList<Predmet> getPredmetyByVyucujiciRok() {
        ArrayList<Predmet> predmetyNew = new ArrayList<>();
        if (loggedUser.getVyucujici() == null) {
            return predmetyNew;
        }
        for (Predmet predmet : predmetyList) {
            for (Vyucujici vyucujici : predmet.getVyucujici()) {
                if (comboSelectRozvrh.getSelectionModel().getSelectedItem().getId()
                        == vyucujici.getId()
                        && comboRozvrhRok.getSelectionModel().getSelectedItem().getRok() == predmet.getPlan().getRok().getRok()) {
                    predmetyNew.add(predmet);
                    continue;
                }
            }
        }

        return predmetyNew;
    }

    private ArrayList<Vyucujici> getVyucujiciWithoutUser() {
        ArrayList<Vyucujici> vyucujiciNew = new ArrayList<>();
        vyucujiciNew.addAll(vyucujiciList);
        for (User user : userList) {
            for (Vyucujici vyucujici : vyucujiciList) {
                if (user.getVyucujici() != null) {
                    if (user.getVyucujici().getId() == vyucujici.getId()) {
                        vyucujiciNew.remove(vyucujici);
                    }
                }
            }
        }
        return vyucujiciNew;
    }

}
