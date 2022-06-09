<?php
    //getting the database connection
    require_once 'includes/db-config.php';
    
    //an array to display response
    $response = array();
  
    $stuID = $_POST['stuID'];

    $stmt = $conn->prepare("SELECT stu_id, nic, first_name, last_name, gender, dob, address, mob_number, email, status FROM tbl_student WHERE stu_id = ?");
    $stmt->bind_param("s",$stuID);
    $stmt->execute();
    $stmt->store_result();
    if($stmt->num_rows > 0){
    $stmt->bind_result($stu_id, $nic, $first_name, $last_name, $gender, $dob, $address, $mob_number, $email, $status);
    $stmt->fetch();
    $user = array(
    'stu_id'=>$stu_id,
    'nic'=>$nic,
    'first_name'=>$first_name,
    'last_name'=>$last_name,
    'gender'=>$gender,
    'dob'=>$dob,
    'address'=>$address,
    'mob_number'=>$mob_number,
    'email'=>$email,
    'status'=>$status
    );  
   
    $response['error'] = false;   
    $response['message'] = 'Request Successfully Completed!'; 
    $response['user'] = $user;   
 }  
 else{  
    $response['error'] = false;   
    $response['message'] = 'Invalid user id!';  
 }

echo json_encode($response);
?>