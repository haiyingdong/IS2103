/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author donghaiying
 */
@Remote
public interface DUSBeanRemote {
    public void update(String id, int longtitude, int latitude);
    public void end(String id, Date date, int longtitude, int latitude);
}
