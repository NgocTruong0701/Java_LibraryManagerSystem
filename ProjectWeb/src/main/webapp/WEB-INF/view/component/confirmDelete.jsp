<!-- Modal Confirmation for Deleting -->
<div class="modal fade" id="deleteConfirmationModal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header text-bg-danger bg-gradient">
				<h1 class="modal-title fs-5" id="deleteConfirmationModalLabel">
					<i class="fa-solid fa-triangle-exclamation"></i> Xác nhận xóa
				</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body text-danger text-center">
				<h5>Bạn có chắc chắn muốn xóa mục đã chọn?</h5>
			</div>
			<div class="modal-footer text-bg-light bg-gradient">
				<form action="${pageContext.request.contextPath}/section/delete"
					method="post" id="deleteForm">
					<input type="hidden" id="deleteItemId" name="itemId" value="">
					<button type="submit" class="btn btn-danger">
						<i class="fa-solid fa-trash"></i> Xóa
					</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">
						<i class="fa-solid fa-x"></i> Hủy
					</button>
				</form>
			</div>
		</div>
	</div>
</div>
