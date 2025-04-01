pragma circom 2.0.0;
include "../node_modules/circomlib/circuits/poseidon.circom";

template ProofOfHash() {
    signal input preimage[2];     // private - 2 preimages or secrets. eg receiverAddr, amount
    signal input hash;            // public

    component hasher = Poseidon(2);
    hasher.inputs[0] <== preimage[0];
    hasher.inputs[1] <== preimage[1];

    hash === hasher.out;
}

component main = ProofOfHash();
