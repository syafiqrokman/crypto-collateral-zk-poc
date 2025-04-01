pragma circom 2.0.0;
include "circomlib/poseidon.circom";

template ProofOfHash() {
    signal input preimage[1];     // private details of txn, just 1 "preimage" for now
    signal input hash;            // public

    component hasher = Poseidon(1);
    hasher.inputs[0] <== preimage[0];

    hash === hasher.out;
}

component main = ProofOfHash();
