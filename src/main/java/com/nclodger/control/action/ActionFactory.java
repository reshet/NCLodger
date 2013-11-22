package com.nclodger.control.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Iaroslav
 * Date: 21.10.13
 * Time: 21:26
 */
public class ActionFactory {

    private static final Map<String,Action> actions;

    static{
        actions = new HashMap<String, Action>();
        actions.put("/signup", new SignUpAction());
        actions.put("/signin", new SignInAction());
        actions.put("/signout", new SignOutAction());
        actions.put("/search", new SearchAction());
        actions.put("/smsettings", new SmSettingsAction());
        actions.put("/adsettings", new SmSettingsAction());
        actions.put("/confirmation", new ConfirmationAction());
        actions.put("/smgetallusers", new SmGetAllUsersAction());
        actions.put("/acdetails", new ACDetailsAction());
        actions.put("/orderstart", new OrderStartAction());
        actions.put("/orderfinish", new OrderFinishAction());
        actions.put("/generatepromo", new GeneratePromoAction());
        actions.put("/makevip", new MakeVIPAction());
        actions.put("/showmostpopularhotel", new getPopHotelsAction());
    }

    public static Action getAction(HttpServletRequest request) {

        return actions.get(request.getServletPath());
    }
}
