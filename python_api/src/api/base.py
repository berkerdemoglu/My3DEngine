from abc import ABC, abstractmethod

from typing import Dict


class BaseAPIClass(ABC):
	"""
	The base class that all classes in the API implement. It provides helper 
	functions and abstract methods that allow the class to be 'JSON-ized'.
	"""

	def _parse_kwargs(self, kwargs) -> None:
		"""
		Parse and set attributes if they are in the possible keyword arguments list, possible_kwargs.
		"""
		try:
			possible_kwargs = self.__class__.possible_kwargs
		except AttributeError:
			raise AttributeError(f'This class does not have possible_kwargs defined')
		else:
			for k, v in kwargs.items():
				if k in possible_kwargs:
					setattr(self, k, v)

	@abstractmethod
	def as_dict(self) -> Dict:
		"""Return the object as a dictionary, ready to be converted to JSON.""" 
		raise NotImplemented
