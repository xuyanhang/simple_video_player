�}�)  a   �	~���Y������ԝju
��*�n]N�U������s*T��Y�]!'SWP���{���H
���S�+�E6�	��@T;�X7!uBm��1��2���f!�� �Qf}l����I/�5�1}��ƣ���&ό�VK�TvO
����%>:z>�UP�9�yb3e�)X}�b']v���C��SS��'��N���O�Y��;t<M�倯��x�׮u4�#�D�ƹ\���wN䟓�;�i�+�A�c%�&�E=N`�>*{ߵ~�+K�ݶM��S,���{I`�v�8��B��{NPA����Y���l�fO�+s�I~.�(�<���	Z�qBu%����p��u��������Jci�J�Ea�eE���� ���"QO,��0����laK�m_�D��9<�3�R�i՗g�j�{��kL�%��p)$��5��P�w]�D�����A�]e�45S�b��z"A-�A��U)�q�
���(�t�Z԰���_ ll�#-��5]���d�G�$�B}�v	��\�k6pMpCe:K��?��![�#��ӯ͔���#|�1I�e���:���@��_���R¡�?6��_��)�������ͳ��M��K��{ap�$�F��
�*�1v���ȅ�ҭ�0%9���gvpZ �i�a�]��t?�ڛ{�;K�!\lU��N
:<�H��:�/\L/7EfJ�P Ipw �
��=Y���}T�o����H��X�_֛�N��s�p)�?nmĈ��^8�?>�8�3��m���h��7��>��+t�:c�O���g�:�3�,s����3��T'Z�)��|�,>�t�Z�lY�1��)'BA���m*Vэϼ��&�h��P�Ĩ=c�G�j����o����3qd9<$8e��B�����vh5K<f�B�$�LWo�(�{2\����-���<�^��� �@��Vw���{Ob"�Y���R�6�_���L��JX�>S+N/,n�hc>����;{��a��%��I, 
	 */
	public VlcjSimpleVideoPlayerService() {
		super();

		new NativeDiscovery().discover();
	}

	public void actionPlay() {
		LinkedList<String> videoPaths = new VideoFilesPathLoader().loadVideoPaths(videoFilesListFilePath);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VlcjSimpleVideoPlayer(videoPaths.toArray(new String[videoPaths.size()]), iconImageFilePath);
			}
		});
	}

}
