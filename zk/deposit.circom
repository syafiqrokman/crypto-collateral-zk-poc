pragma circom 2.0.0;

template Deposit() {
    signal input client_id;
    signal input deposit_amount;
    signal output new_balance;

    new_balance <== client_id + deposit_amount;
}

component main = Deposit();
