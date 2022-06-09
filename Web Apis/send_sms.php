<?php
    // The bundled autoload from the Twilio PHP Helper Library
    require __DIR__ . '/src/Twilio/autoload.php';
    use Twilio\Rest\Client;

    //Fetch URL parameters
    $to = $_POST['to'];
    $msg = $_POST['msg'];

    // Account SID and Auth Token
    $account_sid = 'ACf60c8d5bd9394143b70ef485068c1b99';
    $auth_token = '9212c5f5e4d1b8a33c8e0f69bd119f42';

    // The Twilio number with SMS capabilities
    $twilio_number = "+19206787571";

    $client = new Client($account_sid, $auth_token);

    $client->messages->create(
        // Where to send a text message
        '+94'.$to,
        array(
            'from' => $twilio_number,
            'body' => $msg
        )
    );
?>