�}�)  _   mM�*#9(������}��
���R������'Կ��㋌؞���I�d�R=t���R�1�P�'h�e!�?n�+s{���4ѲA�T+��Qz a� �8�Os}�-(��
���;Wf��k�B *��4̮Y��d�'-�����\2�j�=��T��'��Z��D� �s4n.���a�w�� A?��>�R ˃�~|w���oƯ�����4�M�e��T\�js�;�O����v" �h�r[L��r��k�3�	՛V��g�!6xYQ\�u��t��)��v�W+uZ<B��{NPA����Y���l�fO�+s�I~.�(�<���	Z�qBu%����p��u��������Jci�J�Ea�eE���� ���"QO,��0����laK�m_�D��9<�3�R�i՗g�j�{��kL�%��p)$��5��P�w]�D�����A�]e�45S�b��z"A-�A��U)�q�
���(�t�Z԰���_ lonContext() // Get_the_context_for_beans
				.getBeansOfType(ApplicationReadyInitializer.class) // Get_target_beans
				.values() // Get_all_beans_values
				.stream() // Get_the_stream
				.sorted((v1, v2) -> Float.compare(v2.getPriority(), v1.getPriority())) // Sort_the_priority
				.forEach(v -> v.initialize()); // Action_the_initialize
	}

}
