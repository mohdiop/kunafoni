package impltest;

import com.mohdiop.database.daosimpls.CanalDiffusionDAOImpl;
import com.mohdiop.database.models.CanalDiffusion;

import java.sql.SQLException;
import java.util.ArrayList;

public class CanalDiffusionDAOImplTest {

    private final CanalDiffusionDAOImpl test = new CanalDiffusionDAOImpl();

    public void addNewCanal(String nom) throws SQLException {
        if (test.createNewCanal(new CanalDiffusion(nom))) {
            System.out.println("Canal ajouté avec succès!");
        } else {
            System.out.println("Canal non ajouté!");
        }
    }

    public void getChannelById(int id) throws SQLException {
        CanalDiffusion channel = test.getCanalById(id);
        System.out.println("\n---------------------------------------------");
        System.out.printf("""
                ID        : %s
                Nom       : %s
                """, channel.getId(), channel.getTitre());
        System.out.println("---------------------------------------------");
    }

    public void deleteChannelId(int id) throws SQLException{
        if(test.deleteCanalById(id)){
            System.out.println("Canal supprimé avec succès!");
        } else {
            System.out.println("Canal non supprimé!");
        }
    }

    public void getAllChannels() throws SQLException{
        ArrayList<CanalDiffusion> channels = test.getAllCanal();
        System.out.println("\n---------------------------------------------");
        for(CanalDiffusion channel : channels){
            System.out.printf("""
                ID        : %s
                Nom       : %s
                """, channel.getId(), channel.getTitre());
            System.out.println("---------------------------------------------");
        }
    }
}
