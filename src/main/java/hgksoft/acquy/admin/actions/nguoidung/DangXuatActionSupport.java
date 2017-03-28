/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hgksoft.acquy.admin.actions.nguoidung;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author HNTIN
 */
public class DangXuatActionSupport extends ActionSupport {
    
    public DangXuatActionSupport() {
    }
    
    public String execute() throws Exception {
        ActionContext.getContext().getSession().clear();
        return SUCCESS;
    }
    
}
