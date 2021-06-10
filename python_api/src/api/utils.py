from typing import Union, Dict, Sequence

from .base import BaseAPIClass  # for typing


def to_dict(o: BaseAPIClass) -> Union[Dict, None]:
	"""Convert an object to dict if it is not None."""
	return o.as_dict() if o is not None else None


def to_dict_seq(o_list: Sequence[BaseAPIClass]) -> Sequence[Dict]:
	"""Convert a list of objects into a list of dicts."""
	return [to_dict(o) for o in o_list]
