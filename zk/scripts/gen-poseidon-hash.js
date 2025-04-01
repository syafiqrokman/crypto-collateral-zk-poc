const circomlibjs = require("circomlibjs");
const fs = require("fs");

(async () => {
  const poseidon = await circomlibjs.buildPoseidon();
  const preimage = [1234n, 5678n];
  const hash = poseidon(preimage);
  const hashHex = poseidon.F.toString(hash);

  console.log("✅ Poseidon Hash:", hashHex);

  const input = {
    preimage: preimage.map(n => n.toString()),
    hash: hashHex.toString()
  };

  fs.writeFileSync("inputs/input.json", JSON.stringify(input, null, 2));
})();
