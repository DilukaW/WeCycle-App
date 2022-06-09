<?php
    //getting the database connection
    require_once 'includes/db-config.php';
    
    //an array to display response
    $response = array();
  
    $email = $_POST['email'];
    $password = $_POST['password'];

    $stmt = $conn->prepare("SELECT stu_id, nic, first_name, last_name, mob_number, email, status FROM tbl_student WHERE email = ? AND password = ?");
    $stmt->bind_param("ss",$email, $password);
    $stmt->execute();
    $stmt->store_result();
    if($stmt->num_rows > 0){
    $stmt->bind_result($stu_id, $nic, $first_name, $last_name, $mob_number, $email, $status);
    $stmt->fetch();
    $user = array(
    'stu_id'=>$stu_id,
    'nic'=>$nic,
    'first_name'=>$first_name,
    'last_name'=>$last_name,
    'mob_number'=>$mob_number,
    'email'=>$email,
    'status'=>$status
    );  
   
    $response['error'] = false;   
    $response['message'] = 'Login Successful!';   
    $response['user'] = $user;   
 }  
 else{  
    $response['error'] = false;   
    $response['message'] = 'Invalid username or password!';  
 }

echo json_encode($response);
?>