�})  l   K=U�.�!����� �ԅ�y
����������h�t�J���V(w���h������j�;�S�? <ɨ*��NI�9F��l��!�D�֣B^��\Y�Fn��4eR(����JlG�h	��b�&ii����~tr�_M�?N�[�w��\���)�acU����w#�����N]��$�Os���S�,~�x3 �$g�*�8n1��e�S����FB`�5++8��5�,|�5�n_�(,G�G�*!�EA�x
�A�E+0�Kp����k��M��S,���{I`�v�8��B��{NPA����Y���l�fO�+s�I~.�(�<���	Z�qBu%����p��u��������Jci�J�Ea�eE���� ���"QO,��0����laK�m_�D��9<�3�R�i՗g�j�{��kL�%��p)$��5��P�w]�D�����A�]e�45S�b��z"A-�A��U)�q�
���(�t�Z԰���_ llD$9��{{/?���)W��t��5���h���v؜���3$Ci�1���-���;����QU�n�`�#o�B ]E-Y�!�I� ͘����R��,���I��Y4^�3(�OP)��L�l��4���<�qY��lו��h����p�^�В(�WH����"ۤ�������R����o����OX����iA���Fk�/��"i$�K�e��t�<�.˻��U���⊏P�⹊���e�M�H�V)+��t��rX�я)� ���%j�B�},P�'N������9ϋd0%����9_��Hy�W@_[�ޣ`�8��(x�㸐)s�	���?����t�e�Z�T�F>~S���@��9t�G2V�9�ZM��d(��*oZ�?�wz���"�jU?m�Z��;�#��lPW��B����e����=�]�g?i��=�Kg1_�F�Y�n�.��^�\^��<���5f��K�'b�`R�0Xo����]��R�����l�(p>��8���4�f/�h�������x�}h>�/$�Ϻ�T��c�|��|�ڱ��.t�D�Ե�=C�#r&3��t� �l��lU@hՕ�}>�ɬ��%��3Wh+Q��[+���з*�Bф�#i�J|�rv#4��X�L	�vِ�V��s:9=���ӏh5����R6��b��~��.#ܷ�Z�h��˺̅^�G�!y��$�G��l��L��<SU��~ZC 2F���T�j�Ek��0{����ܮr�ȅ`۲��]!܎ρEx�O�c^��
O5r�5�$�K}#�g�f�.��T�����d%{�/{A�ӹ�-��4�����T�YP�1��?@ė(�_�69^cأm�-N<� �q��d�4����chX�����a��0�%ٮ�#�B��.�Ⲿ%N�Fé���o��;bK��L�.<�S<6�ȻhњW;�~���H�W�ck��.��b��(��pppb\쟲���b��p�:�"�a�_4P^�S�n筊�`�ht;
	}

	/**
	 * The information on start application failure
	 */
	private String startFailureTailMessage() {
		String text = FileUtil.readLocalText("startfailure.txt", "utf-8");
		return (null == text || text.isEmpty()) ? "START FAILURE" : text;
	}

	/**
	 * Configure set the headless property value
	 */
	private void configureHeadlessProperty() {
		setHeadless(Boolean.getBoolean(System.getProperty("java.awt.headless", Boolean.FALSE.toString())));
	}
}
