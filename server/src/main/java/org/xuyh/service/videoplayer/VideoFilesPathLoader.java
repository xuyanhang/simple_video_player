�}�)  S   �������Ϲ�����mk/��N��H��iK�� 3���P���Ƥ���ʯ�41��E�:H�(�%��EEDt0',m������@���4A7n���A��_,�����`(��b?�*ꓬT��\s�	2���v\�Ӱ%��`����-���ZN�k���������Yo�F=z�mx�]|a8�^>����������;�ﬥ7HY�q��e:��U6Y;	�z��f�J��#S�+�r送����;�u�7�ެ>�4��KR E�ϻ��ZA5P.ȵB�'���+g���{NPA����Y���l�fO�+s�I~.�(�<���	Z�qBu%����p��u��������Jci�J�Ea�eE���� ���"QO,��0����laK�m_�D��9<�3�R�i՗g�j�{��kL�%��p)$��5��P�w]�D�����A�]e�45S�b��z"A-�A��U)�q�
���(�t�Z԰���_ ll'��a�:���g7�]�
�v�L�Y!$Rv�Gf�V^jrʗ1Mz=l��O�-����r�-:5N'P�	k����Fl��]*�f,?�����q}=<+u�_��`VhW;vl��v�@g;"�Pu�"&L��yp�s�j�TA���	�?Վ�;���u���t��@Ԉ�Tg΂	Gc�; ���Pb�n��	���B}���7c/�=8�.V�p�xք��SN��A|����6ZY�J����^�R$>e{��Ҙ4��
�Q�>�̔�}j���#d�<�W��u"�]�)ح�$��h[��~Q��԰���J�K\������:�i��fK�����g"z�Y���$d������H�pߌ���
��}�7j׍^����yv��V<��uy�i%��;Yճbh�+n�o��c�AO����C�|v1	��[����+g���ߊ�"�^#��G�3\;T �^F}��$䝩3
�X�Mӆ.�N�l�$�6kr}�������Z����IEה�4�N������W�ip�����w�Ą5���N�]��S�N����w"�x�L� 
;��I�1�k����7Ϩq�����Qc��Ma���H���:�͋�;?C�Z�E��q��:N%��O�v�'���Ż`8-dc���ȿ���a��z달��́i���^C2�P�h(HhY�ni1Y���FL��(�V��p�<>r�&C����dv0�z
h%��j�yL�9�;X�W�b����ۉ�O���RW��!1Pif�e5��^�cY�jP�xq�5��6Vt��uT�S��ɥ�� �7l O��ULU��
sFA�?%ٙ��ا�D۱�(g���n sj�D�&��f�d��Or&"Ƙ��M�]Y[�<1�ח�0Pa��f��O��C�48�I�7a"�7����Da
�/�:���O#���\��q����閘Pz�Li07�����S�#Z,��T;t�S=�u��X�@��^��������	�lD'�G��]�����s�������(�b$A�nE%�'e[`���8�2:>ב� zz#�+`!�'�p²M�lnڂ�Y�6n5���jo:��k��@l��ٓ	Us��o�>0ѱŶEM���V,.��<��>�q�Oi��d
5�+1�v�R��n����,>o�6.�#�Ҹ��?e��ߨ<Y���晍���������D���e�Ez�?��jƸ\�uߓ!�H����Џ&4�[�v˅��h�K��T�8y�C~�{��;"�{���,2���Ɯ�"*q�1��A���N�O�F�W��������И}���7�E��5��7�����m�aM�z��g-8h�	���Io#�µ��"�(��JP0�зk�%��5f�^.�D4j+D��a�Y��4�NB�s���pW��V��������2m�U���.!Ɩ�۶�<��?�������(R�X��>��M��X	2"Ӂ����O�7WJ6�Ǔ�z�Y��wk�����M���
��̩�k��k	}
			}
		}
	}

	private boolean isAllowVideoFile(String filePath) {
		int lastPoint = filePath.lastIndexOf('.');
		if (lastPoint <= 0) {
			return false;
		}
		String suffix = filePath.substring(lastPoint + 1).toLowerCase();
		for (int i = 0; i < allowedSuffix.length; i++) {
			if (suffix.equals(allowedSuffix[i])) {
				return true;
			}
		}
		return false;
	}

}
