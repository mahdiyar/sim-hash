public class JenkinsHash {

    public JenkinsHash() {}

    /**
     * Returns a 64-bit hash value.
     * 
     * @return 64-bit hash value
     */
    public long hash64(byte[] input) {
        int pc = 0;
        int pb = 0;

        return hash(input, input.length, pc, pb, false);
    }

    /**
     * Returns a 32-bit hash value.
     * 
     * @return 32-bit hash value
     */
    public int hash32(byte[] input) {
        int pc = 0;
        int pb = 0;

        return (int) hash(input, input.length, pc, pb, true);
    }

    /**
     * Hash algorithm.
     * 
     * @param k message on which hash is computed
     * @param length message size
     * @param pc primary init value
     * @param pb secondary init value
     * @param is32BitHash true if just 32-bit hash is expected.
     * @return
     */
    private long hash(byte[] k, int length, int pc, int pb, boolean is32BitHash) {
        int a, b, c;

        a = b = c = 0xdeadbeef + length + pc;
        c += pb;

        int offset = 0;
        while (length > 12) {
            a += k[offset + 0];
            a += k[offset + 1] << 8;
            a += k[offset + 2] << 16;
            a += k[offset + 3] << 24;
            b += k[offset + 4];
            b += k[offset + 5] << 8;
            b += k[offset + 6] << 16;
            b += k[offset + 7] << 24;
            c += k[offset + 8];
            c += k[offset + 9] << 8;
            c += k[offset + 10] << 16;
            c += k[offset + 11] << 24;

            // mix(a, b, c);
            a -= c;
            a ^= rot(c, 4);
            c += b;
            b -= a;
            b ^= rot(a, 6);
            a += c;
            c -= b;
            c ^= rot(b, 8);
            b += a;
            a -= c;
            a ^= rot(c, 16);
            c += b;
            b -= a;
            b ^= rot(a, 19);
            a += c;
            c -= b;
            c ^= rot(b, 4);
            b += a;

            length -= 12;
            offset += 12;
        }

        switch (length) {
        case 12:
            c += k[offset + 11] << 24;
        case 11:
            c += k[offset + 10] << 16;
        case 10:
            c += k[offset + 9] << 8;
        case 9:
            c += k[offset + 8];
        case 8:
            b += k[offset + 7] << 24;
        case 7:
            b += k[offset + 6] << 16;
        case 6:
            b += k[offset + 5] << 8;
        case 5:
            b += k[offset + 4];
        case 4:
            a += k[offset + 3] << 24;
        case 3:
            a += k[offset + 2] << 16;
        case 2:
            a += k[offset + 1] << 8;
        case 1:
            a += k[offset + 0];
            break;
        case 0:
            return is32BitHash ? c : (c | ((long) (b << 32)));
        }

        // Final mixing of thrree 32-bit values in to c
        c ^= b;
        c -= rot(b, 14);
        a ^= c;
        a -= rot(c, 11);
        b ^= a;
        b -= rot(a, 25);
        c ^= b;
        c -= rot(b, 16);
        a ^= c;
        a -= rot(c, 4);
        b ^= a;
        b -= rot(a, 14);
        c ^= b;
        c -= rot(b, 24);

        return is32BitHash ? c : (c | ((long) (b << 32)));
    }

    long rot(int x, int distance) {
        return (x << distance) | (x >> (32 - distance));
        // return (x << distance) | (x >>> -distance);
    }
}
