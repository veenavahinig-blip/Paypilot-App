package com.hcl.paypilot.dto;


import java.util.List;


import com.hcl.paypilot.entity.BillEntity;


/**

* ============================================================================

* User Dashboard DTO

* ============================================================================

*

* This Data Transfer Object (DTO) is used to provide dashboard-related

* information for a user in the PayPilot Application.

*

* The dashboard summarizes:

* - User information

* - Total bills count

* - Upcoming bills count

* - Overdue bills count

* - Paid bills count

* - Recently created or updated bills

*

* This DTO is primarily used by the Dashboard API and transferred

* between the Service Layer and Controller Layer.

*

* Author: PayPilot Team

* ============================================================================

*/

public class UserDashboardDTO {


    /**

     * Unique identifier of the user.

     */

    private String userId;


    /**

     * User's full name.

     */

    private String userName;


    /**

     * User's registered email address.

     */

    private String userEmail;


    /**

     * Total number of bills associated with the user.

     */

    private Integer totalBills;


    /**

     * Number of upcoming bills.

     */

    private Integer upcomingBills;


    /**

     * Number of overdue bills.

     */

    private Integer overdueBills;


    /**

     * Number of bills that have already been paid.

     */

    private Integer paidBills;


    /**

     * List of recently created or updated bills.

     */

    private List<BillEntity> recentBills;


    /**

     * Default constructor.

     */

    public UserDashboardDTO() {


    }


    /**

     * Retrieves the user identifier.

     *

     * @return User ID

     */

    public String getUserId() {


        return userId;


    }


    /**

     * Sets the user identifier.

     *

     * @param userId User ID

     */

    public void setUserId(String userId) {


        this.userId = userId;


    }


    /**

     * Retrieves the user name.

     *

     * @return User Name

     */

    public String getUserName() {


        return userName;


    }


    /**

     * Sets the user name.

     *

     * @param userName User Name

     */

    public void setUserName(String userName) {


        this.userName = userName;


    }


    /**

     * Retrieves the user email address.

     *

     * @return User Email

     */

    public String getUserEmail() {


        return userEmail;


    }


    /**

     * Sets the user email address.

     *

     * @param userEmail User Email

     */

    public void setUserEmail(String userEmail) {


        this.userEmail = userEmail;


    }


    /**

     * Retrieves the total bill count.

     *

     * @return Total Bills

     */

    public Integer getTotalBills() {


        return totalBills;


    }


    /**

     * Sets the total bill count.

     *

     * @param totalBills Total Bills

     */

    public void setTotalBills(Integer totalBills) {


        this.totalBills = totalBills;


    }


    /**

     * Retrieves the upcoming bill count.

     *

     * @return Upcoming Bills

     */

    public Integer getUpcomingBills() {


        return upcomingBills;


    }


    /**

     * Sets the upcoming bill count.

     *

     * @param upcomingBills Upcoming Bills

     */

    public void setUpcomingBills(Integer upcomingBills) {


        this.upcomingBills = upcomingBills;


    }


    /**

     * Retrieves the overdue bill count.

     *

     * @return Overdue Bills

     */

    public Integer getOverdueBills() {


        return overdueBills;


    }


    /**

     * Sets the overdue bill count.

     *

     * @param overdueBills Overdue Bills

     */

    public void setOverdueBills(Integer overdueBills) {


        this.overdueBills = overdueBills;


    }


    /**

     * Retrieves the paid bill count.

     *

     * @return Paid Bills

     */

    public Integer getPaidBills() {


        return paidBills;


    }


    /**

     * Sets the paid bill count.

     *

     * @param paidBills Paid Bills

     */

    public void setPaidBills(Integer paidBills) {


        this.paidBills = paidBills;


    }


    /**

     * Retrieves the list of recent bills.

     *

     * @return List of recent bills

     */

    public List<BillEntity> getRecentBills() {


        return recentBills;


    }


    /**

     * Sets the list of recent bills.

     *

     * @param recentBills List of recent bills

     */

    public void setRecentBills(List<BillEntity> recentBills) {


        this.recentBills = recentBills;


    }


}
 