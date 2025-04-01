// zk/circuits/hash.circom
pragma circom 2.1.5;

template HashCheck() {
    signal input a;
    signal input b;
    signal output c;

    c <== a + b; // Placeholder logic for now
}

component main = HashCheck();