import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Member } from './member.model';
import { MemberService } from './member.service';

@Injectable()
export class MemberPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private memberService: MemberService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.memberService.find(id)
                    .subscribe((memberResponse: HttpResponse<Member>) => {
                        const member: Member = memberResponse.body;
                        member.dateofbirth = this.datePipe
                            .transform(member.dateofbirth, 'yyyy-MM-ddTHH:mm:ss');
                        if (member.datecreated) {
                            member.datecreated = {
                                year: member.datecreated.getFullYear(),
                                month: member.datecreated.getMonth() + 1,
                                day: member.datecreated.getDate()
                            };
                        }
                        if (member.memberfrom) {
                            member.memberfrom = {
                                year: member.memberfrom.getFullYear(),
                                month: member.memberfrom.getMonth() + 1,
                                day: member.memberfrom.getDate()
                            };
                        }
                        if (member.dateinactive) {
                            member.dateinactive = {
                                year: member.dateinactive.getFullYear(),
                                month: member.dateinactive.getMonth() + 1,
                                day: member.dateinactive.getDate()
                            };
                        }
                        if (member.lastupdated) {
                            member.lastupdated = {
                                year: member.lastupdated.getFullYear(),
                                month: member.lastupdated.getMonth() + 1,
                                day: member.lastupdated.getDate()
                            };
                        }
                        this.ngbModalRef = this.memberModalRef(component, member);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.memberModalRef(component, new Member());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    memberModalRef(component: Component, member: Member): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.member = member;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
