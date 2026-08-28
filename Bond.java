// 2つの原子を繋ぐ「バネ（結合）」を表すクラス
class Bond {
    public Atom atom1, atom2;
    public float springConstant, restLength;

    public Bond(Atom a1, Atom a2, float k, float len) {
        this.atom1 = a1; this.atom2 = a2; this.springConstant = k; this.restLength = len;
    }

    public void applySpringForce() {
        float dx = atom2.position.x - atom1.position.x;
        float dy = atom2.position.y - atom1.position.y;
        float dz = atom2.position.z - atom1.position.z;
        float distance = (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
        if (distance == 0) return;

        float displacement = distance - restLength;
        float forceMagnitude = springConstant * displacement;
        Vector3 forceVector = new Vector3(dx, dy, dz).normalized().multiply(forceMagnitude);

        atom1.addForce(forceVector);
        atom2.addForce(forceVector.multiply(-1.0f));
    }
}