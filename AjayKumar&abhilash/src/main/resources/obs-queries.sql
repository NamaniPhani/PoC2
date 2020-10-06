

INSERT_EMPLOYMENT_DETAILS_STMT = INSERT into employmentdetails(empId,empName,empSal,addres,mobileNumber) values(?,?,?,?,?);

 GET_EMPLOYMENT_DETAILS_STMT = select * from employmentdetails;

 UPDATE_EMPLOYMENT_DETAILS_STMT = UPDATE employmentdetails set  empName=?,empSal=?,addres=?,mobileNumber=? where empId=?; 

DELETE_EMPLOYMENT_DETAILS_STMT = DELETE from employmentdetails  where empId=?;


GET_EMPLOYMENT_DETAILS_BY_ID_STMT=select * from employmentdetails where empId=?;
